package org.feather.ecommerce.stream.featherAuto;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.stream.featherAuto
 * @className: FeatherSource
 * @author: feather
 * @description:自定义输出信道
 * @since: 2023-09-06 21:19
 * @version: 1.0
 */

public interface FeatherSource {

    String OUTPUT="featherOutPut";

    /**
     * 输出信道的名称 是 featherOutput 需要使用Stream  绑定器 在yml 中声明
     * @return
     */
    @Output(FeatherSource.OUTPUT)
    MessageChannel  featherOutput();

}
