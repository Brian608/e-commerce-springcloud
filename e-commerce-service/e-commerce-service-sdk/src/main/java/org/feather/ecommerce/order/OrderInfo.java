package org.feather.ecommerce.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.feather.ecommerce.goods.DeductGoodsInventory;

import java.util.List;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.order
 * @className: OrderInfo
 * @author: feather
 * @description:
 * @since: 2023-09-11 21:10
 * @version: 1.0
 */
@ApiModel(description = "用户发起购买订单")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderInfo {

    @ApiModelProperty(value = "用户地址表主键 id")
    private Long userAddress;

    @ApiModelProperty(value = "订单中的商品信息")
    private List<OrderItem> orderItems;

    /**
     * <h2>订单中的商品信息</h2>
     * */
    @ApiModel(description = "订单中的单项商品信息")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItem {

        @ApiModelProperty(value = "商品表主键 id")
        private Long goodsId;

        @ApiModelProperty(value = "购买商品个数")
        private Integer count;

        public DeductGoodsInventory toDeductGoodsInventory() {
            return new DeductGoodsInventory(this.goodsId, this.count);
        }
    }

}
