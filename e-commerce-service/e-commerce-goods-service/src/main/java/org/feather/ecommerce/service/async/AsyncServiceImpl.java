package org.feather.ecommerce.service.async;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.feather.ecommerce.constant.GoodsConstant;
import org.feather.ecommerce.dao.EcommerceGoodsDao;
import org.feather.ecommerce.entity.EcommerceGoods;
import org.feather.ecommerce.goods.GoodsInfo;
import org.feather.ecommerce.goods.SimpleGoodsInfo;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service.async
 * @className: AsyncServiceImpl
 * @author: feather
 * @description: 异步服务接口实现
 * @since: 2023-08-17 7:14
 * @version: 1.0
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class AsyncServiceImpl  implements  IAsyncService{
    private final EcommerceGoodsDao ecommerceGoodsDao;
    private final StringRedisTemplate redisTemplate;

    public AsyncServiceImpl(EcommerceGoodsDao ecommerceGoodsDao,
                            StringRedisTemplate redisTemplate) {
        this.ecommerceGoodsDao = ecommerceGoodsDao;
        this.redisTemplate = redisTemplate;
    }

    /**
     * <h2>异步任务需要加上注解, 并指定使用的线程池</h2>
     * 异步任务处理两件事:
     *  1. 将商品信息保存到数据表
     *  2. 更新商品缓存
     * */
    @Async("getAsyncExecutor")
    @Override
    public void asyncImportGoods(List<GoodsInfo> goodsInfos, String taskId) {
        log.info("async task running taskId: [{}]", taskId);

        StopWatch watch = StopWatch.createStarted();
        //1  如果是 goodsInfo 中存在重复的商品，不保存，直接返回，记录错误日志
        //请求数据是否合法的标记
        boolean isIllegal =false;

        //将商品信息字段join 再一起，用来判断是否存在重复
        Set<String>  goodsJoinInfos=new HashSet<>(goodsInfos.size());
        //过滤出来  可以入库的商品信息(规则按照自己业务需求定义即可)
        List<GoodsInfo> filterGoodsInfo=new ArrayList<>(goodsInfos.size());

        //走一遍循环，过滤非法参数与判定当前请求是否合法
        for (GoodsInfo goodsInfo : goodsInfos) {
            //基本条件不满足的，直接过滤掉
            if (goodsInfo.getPrice()<0|| goodsInfo.getSupply()<=0){
                log.info("goods info is invalid :[{}]", JSON.toJSONString(goodsInfo));
                continue;
            }
            //组合每一个商品信息
            String joinInfo =String.format("%s,%s,%s",goodsInfo.getGoodsCategory(),goodsInfo.getBrandCategory(),goodsInfo.getGoodsName());
            if (goodsJoinInfos.contains(joinInfo)){
                isIllegal=true;
            }
            //加入到两个容器中
            goodsJoinInfos.add(joinInfo);
            filterGoodsInfo.add(goodsInfo);
        }
        //如果存在重复商品，或者没有需要入库的商品，直接打印日志返回
        if (isIllegal|| CollectionUtils.isEmpty(filterGoodsInfo)){
            watch.stop();
            log.warn("import nothing: [{}]", JSON.toJSONString(filterGoodsInfo));
            log.info("check and import goods done: [{}ms]",
                    watch.getTime(TimeUnit.MILLISECONDS));
            return;

        }
        List<EcommerceGoods> ecommerceGoodsList=filterGoodsInfo.stream().map(EcommerceGoods::to).collect(Collectors.toList());
        List<EcommerceGoods> targetGoods=new ArrayList<>(ecommerceGoodsList.size());

        //保存goodsInfo 之前先判断下是否存在重复商品

        ecommerceGoodsList.forEach(g->{
            // limit 1
            if (null!=ecommerceGoodsDao.findFirst1ByGoodsCategoryAndBrandCategoryAndGoodsName(
                    g.getGoodsCategory(),g.getBrandCategory(),g.getGoodsName()
            ).orElse(null)){
                return;
            }
            targetGoods.add(g);
        });
        // 商品信息入库
        List<EcommerceGoods> savedGoods = IterableUtils.toList(
                ecommerceGoodsDao.saveAll(targetGoods)
        );

        // 将入库商品信息同步到 Redis 中
        saveNewGoodsInfoToRedis(savedGoods);

        log.info("save goods info to db and redis: [{}]", savedGoods.size());

        watch.stop();
        log.info("check and import goods success: [{}ms]",
                watch.getTime(TimeUnit.MILLISECONDS));

    }

    /**
     * 将保存到数据表中的数据保存到redis 中
     * dict: key -> <id, SimpleGoodsInfo(json)>
     * @param savedGoods
     */
    private void saveNewGoodsInfoToRedis(List<EcommerceGoods> savedGoods) {
        // 由于 Redis 是内存存储, 只存储简单商品信息
        List<SimpleGoodsInfo> simpleGoodsInfoList = savedGoods.stream().map(EcommerceGoods::toSimple).collect(Collectors.toList());
        Map<String, String> id2JsonObject = new HashMap<>(simpleGoodsInfoList.size());

        simpleGoodsInfoList.forEach(
                g -> id2JsonObject.put(g.getId().toString(), JSON.toJSONString(g))
        );
        // 保存到 Redis 中
        redisTemplate.opsForHash().putAll(
                GoodsConstant.ECOMMERCE_GOODS_DICT_KEY,
                id2JsonObject
        );

    }

}
