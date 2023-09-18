package org.feather.ecommerce.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.order
 * @className: LogisticsMessage
 * @author: feather
 * @description: 创建订单时发送的物流消息
 * @since: 17-Sep-23 12:03 PM
 * @version: 1.0
 */
@ApiModel(description = "stream 物流消息对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogisticsMessage {

    @ApiModelProperty(value = "用户主键id")
    private Long userId;


    @ApiModelProperty(value = "订单主键id")
    private Long orderId;

    @ApiModelProperty(value = "用户地址主键id")
    private Long addressId;

    @ApiModelProperty(value = "备注信息(json存储)")
    private String extraInfo;

}
