package org.feather.ecommerce.sink;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.sink
 * @className: LogisticsSink
 * @author: feather
 * @description: 自定义物流信息接收器(Sink)
 * @since: 25-Sep-23 10:30 PM
 * @version: 1.0
 */
public interface LogisticsSink {

    /** 输入信道名称 */
    String INPUT = "logisticsInput";

    /**
     * <h2>物流 Sink -> logisticsInput</h2>
     * */
    @Input(LogisticsSink.INPUT)
    SubscribableChannel logisticsInput();
}

