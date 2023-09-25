package org.feather.ecommerce.service;
import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.dao.EcommerceLogisticsDao;
import org.feather.ecommerce.entity.EcommerceLogistics;
import org.feather.ecommerce.order.LogisticsMessage;
import org.feather.ecommerce.sink.LogisticsSink;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service
 * @className: LogisticsServiceImpl
 * @author: feather
 * @description:
 * @since: 25-Sep-23 10:25 PM
 * @version: 1.0
 */
@Slf4j
@EnableBinding(LogisticsSink.class)
public class LogisticsServiceImpl {

    private final EcommerceLogisticsDao logisticsDao;

    public LogisticsServiceImpl(EcommerceLogisticsDao logisticsDao) {
        this.logisticsDao = logisticsDao;
    }

    /**
     * <h2>订阅监听订单微服务发送的物流消息</h2>
     * */
    @StreamListener("logisticsInput")
    public void consumeLogisticsMessage(@Payload Object payload) {

        log.info("receive and consume logistics message: [{}]", payload.toString());
        LogisticsMessage logisticsMessage = JSON.parseObject(
                payload.toString(), LogisticsMessage.class
        );
        EcommerceLogistics ecommerceLogistics = logisticsDao.save(
                new EcommerceLogistics(
                        logisticsMessage.getUserId(),
                        logisticsMessage.getOrderId(),
                        logisticsMessage.getAddressId(),
                        logisticsMessage.getExtraInfo()
                )
        );
        log.info("consume logistics message success: [{}]", ecommerceLogistics.getId());
    }
}
