package org.feather.ecommerce.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.common.TableId;
import org.feather.ecommerce.order.OrderInfo;
import org.feather.ecommerce.service.IOrderService;
import org.feather.ecommerce.vo.PageSimpleOrderDetail;
import org.springframework.web.bind.annotation.*;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.controller
 * @className: OrderController
 * @author: feather
 * @description:
 * @since: 17-Sep-23 8:12 PM
 * @version: 1.0
 */

@Api(tags = "订单服务")
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(
            value = "创建",
            notes = "购买(分布式事务): 创建订单 -> 扣减库存 -> 扣减余额 -> 发送物流消息",
            httpMethod = "POST"
    )
    @PostMapping("/create-order")
    public TableId createOrder(@RequestBody OrderInfo orderInfo) {
        return orderService.createOrder(orderInfo);
    }

    @ApiOperation(
            value = "订单信息",
            notes = "获取当前用户的订单信息: 带有分页",
            httpMethod = "GET"
    )
    @GetMapping("/order-detail")
    public PageSimpleOrderDetail getSimpleOrderDetailByPage(
            @RequestParam(required = false, defaultValue = "1") int page) {
        return orderService.getSimpleOrderDetailByPage(page);
    }
}
