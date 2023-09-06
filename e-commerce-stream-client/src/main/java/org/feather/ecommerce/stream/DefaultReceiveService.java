package org.feather.ecommerce.stream;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.vo.FeatherMessage;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.stream
 * @className: DefaultReceiveService
 * @author: feather
 * @description: 使用默认的信道实现消息 的接受
 * @since: 2023-09-05 22:13
 * @version: 1.0
 */
@Slf4j
@EnableBinding(Sink.class)
public class DefaultReceiveService {

    /**
     * 使用默认的输入信道接受消息
     * @param payload
     */
    @StreamListener(Sink.INPUT)
    public  void  receiveMessage(Object payload){
        log.info("in DefaultReceiveService consume message start");
//        FeatherMessage featherMessage = JSON.parseObject(
//                payload.toString(), FeatherMessage.class
//        );
        // 消费消息
        log.info("in DefaultReceiveService consume message success: [{}]",
                JSON.toJSONString(payload));

    }
}
