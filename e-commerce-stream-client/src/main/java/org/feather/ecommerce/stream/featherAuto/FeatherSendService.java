package org.feather.ecommerce.stream.featherAuto;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.vo.FeatherMessage;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.stream.featherAuto
 * @className: FeatherSendService
 * @author: feather
 * @description: 使用自定义的通信信道
 * @since: 2023-09-06 21:22
 * @version: 1.0
 */
@Slf4j
@EnableBinding(FeatherSource.class)
public class FeatherSendService {

    private final  FeatherSource featherSource;


    public FeatherSendService(FeatherSource featherSource) {
        this.featherSource = featherSource;
    }

    /**
     * 使用自定义的输出信道发送消息
     * @param message
     */
    public  void  sendMessage(FeatherMessage message){
        String _message = JSON.toJSONString(message);
        log.info("in  FeatherSendService  sendMessage result :[{}]",_message);
        featherSource.featherOutput().send(
                MessageBuilder.withPayload(_message).build()
        );
    }
}
