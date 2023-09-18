package org.feather.ecommerce.service;

import org.feather.ecommerce.common.TableId;
import org.feather.ecommerce.order.OrderInfo;
import org.feather.ecommerce.vo.PageSimpleOrderDetail;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service
 * @className: IOrderService
 * @author: feather
 * @description: 订单相关服务接口定义
 * @since: 2023-09-11 21:20
 * @version: 1.0
 */
public interface IOrderService {


    /**
     * <h2>下单(分布式事务): 创建订单 -> 扣减库存 -> 扣减余额 -> 创建物流信息(Stream + Kafka)</h2>
     * */
    TableId createOrder(OrderInfo orderInfo);

    /**
     * <h2>获取当前用户的订单信息: 带有分页</h2>
     * */
    PageSimpleOrderDetail getSimpleOrderDetailByPage(int page);
}
