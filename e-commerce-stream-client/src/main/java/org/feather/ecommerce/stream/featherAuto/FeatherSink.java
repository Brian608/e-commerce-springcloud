package org.feather.ecommerce.stream.featherAuto;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.stream.featherAuto
 * @className: FeatherSink
 * @author: feather
 * @description: 自定义输入信道
 * @since: 2023-09-06 21:29
 * @version: 1.0
 */

public interface FeatherSink {


    String INPUT="featherInput";

    /**
     * 输入信道的名称 需要使用Stream  绑定器 在yml 中声明
     * @return
     */
    @Input(FeatherSink.INPUT)
    SubscribableChannel featherInput();


}
