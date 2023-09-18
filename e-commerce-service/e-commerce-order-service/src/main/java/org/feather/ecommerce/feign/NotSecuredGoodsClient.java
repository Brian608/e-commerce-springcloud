package org.feather.ecommerce.feign;

import org.feather.ecommerce.common.TableId;
import org.feather.ecommerce.goods.DeductGoodsInventory;
import org.feather.ecommerce.goods.SimpleGoodsInfo;
import org.feather.ecommerce.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.feign
 * @className: NotSecuredGoodsClient
 * @author: feather
 * @description: 不安全的商品服务 Feign 接口
 * @since: 2023-09-11 22:02
 * @version: 1.0
 */
@FeignClient(
        contextId = "NotSecuredGoodsClient",
        value = "e-commerce-goods-service"
)
public interface NotSecuredGoodsClient {

    /**
     * <h2>根据 ids 查询简单的商品信息</h2>
     * */
    @RequestMapping(
            value = "/ecommerce-goods-service/goods/deduct-goods-inventory",
            method = RequestMethod.PUT
    )
    CommonResponse<Boolean> deductGoodsInventory(
            @RequestBody List<DeductGoodsInventory> deductGoodsInventories);

    /**
     * <h2>根据 ids 查询简单的商品信息</h2>
     * */
    @RequestMapping(
            value = "/ecommerce-goods-service/goods/simple-goods-info",
            method = RequestMethod.POST
    )
    CommonResponse<List<SimpleGoodsInfo>> getSimpleGoodsInfoByTableId(
            @RequestBody TableId tableId);
}
