package org.feather.ecommerce.feign.hystrix;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.common.TableId;
import org.feather.ecommerce.feign.SecuredGoodsClient;
import org.feather.ecommerce.goods.SimpleGoodsInfo;
import org.feather.ecommerce.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.feign.hystrix
 * @className: GoodsClientHystrix
 * @author: feather
 * @description: 商品服务熔断降级兜底
 * @since: 2023-09-11 22:11
 * @version: 1.0
 */
@Slf4j
@Component
public class GoodsClientHystrix implements SecuredGoodsClient {

    @Override
    public CommonResponse<List<SimpleGoodsInfo>> getSimpleGoodsInfoByTableId(
            TableId tableId) {

        log.error("[goods client feign request error in order service] get simple goods" +
                "error: [{}]", JSON.toJSONString(tableId));
        return new CommonResponse<>(
                -1,
                "[goods client feign request error in order service]",
                Collections.emptyList()
        );
    }
}
