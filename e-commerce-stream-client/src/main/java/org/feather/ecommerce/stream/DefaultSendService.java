package org.feather.ecommerce.stream;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.vo.FeatherMessage;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.stream
 * @className: DefaultSendService
 * @author: feather
 * @description: 使用默认的通讯信道发送消息
 * @since: 2023-09-05 22:07
 * @version: 1.0
 */
@Slf4j
@EnableBinding(Source.class)
public class DefaultSendService {

    private final Source source;

    public DefaultSendService(Source source) {
        this.source = source;
    }

    /**
     * 使用默认的输出信道发送消息
     * @param message
     */
    public void sendMessage(FeatherMessage message){
        String _message = JSON.toJSONString(message);
        log.info("in DefaultSendService send message: [{}]", _message);

        // Spring Messaging, 统一消息的编程模型, 是 Stream 组件的重要组成部分之一
        source.output().send(MessageBuilder.withPayload(_message).build());

    }
}
