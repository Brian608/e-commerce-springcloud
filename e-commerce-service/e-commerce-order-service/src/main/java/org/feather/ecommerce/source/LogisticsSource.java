package org.feather.ecommerce.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;


/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.source
 * @className: LogisticsSource
 * @author: feather
 * @description: 自定义物流消息通信信道(Source)
 * @since: 17-Sep-23 11:58 AM
 * @version: 1.0
 */

public interface LogisticsSource {

    /** 输出信道名称 */
    String OUTPUT = "logisticsOutput";

    /**
     * <h2>物流 Source -> logisticsOutput</h2>
     * 通信信道的名称是 logisticsOutput, 对应到 yml 文件里的配置
     * */
    @Output(LogisticsSource.OUTPUT)
    MessageChannel logisticsOutput();
}


