package org.feather.ecommerce.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.feather.ecommerce.common.TableId;
import org.feather.ecommerce.constant.GoodsConstant;
import org.feather.ecommerce.dao.EcommerceGoodsDao;
import org.feather.ecommerce.entity.EcommerceGoods;
import org.feather.ecommerce.goods.DeductGoodsInventory;
import org.feather.ecommerce.goods.GoodsInfo;
import org.feather.ecommerce.goods.SimpleGoodsInfo;
import org.feather.ecommerce.service.IGoodsService;
import org.feather.ecommerce.vo.PageSimpleGoodsInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service.impl
 * @className: GoodsServiceImpl
 * @author: feather
 * @description:
 * @since: 2023-08-17 22:54
 * @version: 1.0
 */
@Transactional(rollbackFor = Exception.class)
@Slf4j
@Service
public class GoodsServiceImpl implements IGoodsService {

    private final StringRedisTemplate redisTemplate;

    private final EcommerceGoodsDao ecommerceGoodsDao;

    public GoodsServiceImpl(StringRedisTemplate redisTemplate, EcommerceGoodsDao ecommerceGoodsDao) {
        this.redisTemplate = redisTemplate;
        this.ecommerceGoodsDao = ecommerceGoodsDao;
    }


    @Override
    public List<GoodsInfo> getGoodsInfoByTableId(TableId tableId) {
        //详细的商品信息 不能从 redis cache 中获取
        List<Long> ids = tableId.getIds().stream().map(TableId.Id::getId).collect(Collectors.toList());
        log.info("get goods info by ids :[{}]", JSON.toJSONString(ids));
        List<EcommerceGoods> ecommerceGoodsList = IterableUtils.toList(ecommerceGoodsDao.findAllById(ids));
        return ecommerceGoodsList.stream().map(EcommerceGoods::toGoodsInfo).collect(Collectors.toList());
    }

    @Override
    public PageSimpleGoodsInfo getSimpleGoodsInfoByPage(int page) {
        //分页不能从 redis cache 中去拿
        if (page<=1){
            page=1;
        }
        //这里分页规则  一页 有10条数据， 按照id 倒叙排序
        Pageable pageable= PageRequest.of(page-1,10, Sort.by("id").descending());
        Page<EcommerceGoods> orderPage=ecommerceGoodsDao.findAll(pageable);
        //是否还有更多页  总分页数是否大于当前给定的页
        boolean hasMore= orderPage.getTotalPages()>page;
        return new PageSimpleGoodsInfo(orderPage.getContent().stream().map(EcommerceGoods::toSimple).collect(Collectors.toList()), hasMore);
    }

    @Override
    public List<SimpleGoodsInfo> getSimpleGoodsInfoByTableId(TableId tableId) {
        //获取商品的简单信息，可以从redis cache 中去拿，拿不到可以从DB中获取并保存到redis  里面
        //redis 中的kv 都是字符串类型
        List<Object> goodsIds = tableId.getIds().stream().map(i -> i.getId().toString()).collect(Collectors.toList());
        //FIXME 如果 cache 中查不到 goodsId 对应的数据, 返回的是 null, [null, null]
        List<Object> cachedSimpleGoodsInfos = redisTemplate.opsForHash()
                .multiGet(GoodsConstant.ECOMMERCE_GOODS_DICT_KEY, goodsIds)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        // 如果从 Redis 中查到了商品信息, 分两种情况去操作
        if (CollectionUtils.isNotEmpty(cachedSimpleGoodsInfos)) {
            // 1. 如果从缓存中查询出所有需要的 SimpleGoodsInfo
            if (cachedSimpleGoodsInfos.size() == goodsIds.size()) {
                log.info("get simple goods info by ids (from cache): [{}]",
                        JSON.toJSONString(goodsIds));
                return parseCachedGoodsInfo(cachedSimpleGoodsInfos);
            } else {
                // 2. 一半从数据表中获取 (right), 一半从 redis cache 中获取 (left)
                List<SimpleGoodsInfo> left = parseCachedGoodsInfo(cachedSimpleGoodsInfos);
                // 取差集: 传递进来的参数 - 缓存中查到的 = 缓存中没有的
                Collection<Long> subtractIds = CollectionUtils.subtract(
                        goodsIds.stream()
                                .map(g -> Long.valueOf(g.toString())).collect(Collectors.toList()),
                        left.stream()
                                .map(SimpleGoodsInfo::getId).collect(Collectors.toList())
                );
                // 缓存中没有的, 查询数据表并缓存
                List<SimpleGoodsInfo> right = queryGoodsFromDBAndCacheToRedis(
                        new TableId(subtractIds.stream().map(TableId.Id::new)
                                .collect(Collectors.toList()))
                );
                // 合并 left 和 right 并返回
                log.info("get simple goods info by ids (from db and cache): [{}]",
                        JSON.toJSONString(subtractIds));
                return new ArrayList<>(CollectionUtils.union(left, right));
            }
        } else {
            // 从 redis 里面什么都没有查到
            return queryGoodsFromDBAndCacheToRedis(tableId);
        }

    }

    @Override
    public Boolean deductGoodsInventory(List<DeductGoodsInventory> deductGoodsInventories) {
        // 检验下参数是否合法
        deductGoodsInventories.forEach(d -> {
            if (d.getCount() <= 0) {
                throw new RuntimeException("purchase goods count need > 0");
            }
        });

        List<EcommerceGoods> ecommerceGoods = IterableUtils.toList(
                ecommerceGoodsDao.findAllById(
                        deductGoodsInventories.stream()
                                .map(DeductGoodsInventory::getGoodsId)
                                .collect(Collectors.toList())
                )
        );
        // 根据传递的 goodsIds 查询不到商品对象, 抛异常
        if (CollectionUtils.isEmpty(ecommerceGoods)) {
            throw new RuntimeException("can not found any goods by request");
        }
        // 查询出来的商品数量与传递的不一致, 抛异常
        if (ecommerceGoods.size() != deductGoodsInventories.size()) {
            throw new RuntimeException("request is not valid");
        }
        // goodsId -> DeductGoodsInventory
        Map<Long, DeductGoodsInventory> goodsId2Inventory =
                deductGoodsInventories.stream().collect(
                        Collectors.toMap(DeductGoodsInventory::getGoodsId,
                                Function.identity())
                );

        // 检查是不是可以扣减库存, 再去扣减库存
        ecommerceGoods.forEach(g -> {
            Long currentInventory = g.getInventory();
            Integer needDeductInventory = goodsId2Inventory.get(g.getId()).getCount();
            if (currentInventory < needDeductInventory) {
                log.error("goods inventory is not enough: [{}], [{}]",
                        currentInventory, needDeductInventory);
                throw new RuntimeException("goods inventory is not enough: " + g.getId());
            }
            // 扣减库存
            g.setInventory(currentInventory - needDeductInventory);
            log.info("deduct goods inventory: [{}], [{}], [{}]", g.getId(),
                    currentInventory, g.getInventory());
        });

        ecommerceGoodsDao.saveAll(ecommerceGoods);
        log.info("deduct goods inventory done");

        return true;

    }

    /**
     * <h2>将缓存中的数据反序列化成 Java Pojo 对象</h2>
     * */
    private List<SimpleGoodsInfo> parseCachedGoodsInfo(List<Object> cachedSimpleGoodsInfo) {

        return cachedSimpleGoodsInfo.stream()
                .map(s -> JSON.parseObject(s.toString(), SimpleGoodsInfo.class))
                .collect(Collectors.toList());
    }
    /**
     * <h2>从数据表中查询数据, 并缓存到 Redis 中</h2>
     * */
    private List<SimpleGoodsInfo> queryGoodsFromDBAndCacheToRedis(TableId tableId) {
        //从数据表中查询数据并做转换
        List<Long> ids = tableId.getIds().stream().map(TableId.Id::getId).collect(Collectors.toList());
        log.info("get simple goods info by ids(from db):[{}]",JSON.toJSONString(ids));
        List<EcommerceGoods> ecommerceGoodsList=IterableUtils.toList(ecommerceGoodsDao.findAllById(ids));

        List<SimpleGoodsInfo> result=ecommerceGoodsList.stream().map(EcommerceGoods::toSimple).collect(Collectors.toList());
        //将结果缓存，下一次可以直接从redis cache 中获取
        log.info("cache goods info :[{}]",JSON.toJSONString(ids));
        Map<String, String> id2JsonObject =new HashMap<>(result.size());
        result.forEach(g->id2JsonObject.put(g.getId().toString(),JSON.toJSONString(g)));
        //保存到redis 中
        redisTemplate.opsForHash().putAll(GoodsConstant.ECOMMERCE_GOODS_DICT_KEY,id2JsonObject);
        return  result;
    }
}
