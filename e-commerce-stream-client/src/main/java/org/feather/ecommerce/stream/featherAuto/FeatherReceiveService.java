package org.feather.ecommerce.stream.featherAuto;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.vo.FeatherMessage;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.stream.featherAuto
 * @className: FeatherRecveiveService
 * @author: feather
 * @description: 使用自定义的输入信道实现消息的接受
 * @since: 2023-09-06 21:31
 * @version: 1.0
 */
@Slf4j
@EnableBinding(FeatherSink.class)
public class FeatherReceiveService {

    /**
     * 使用自定义的输入信道接受消息
     */
    @StreamListener(FeatherSink.INPUT)
    public  void receiveMessage(@Payload  Object payload){
        log.info("in FeatherReceiveService consume message start ");
      //  FeatherMessage featherMessage = JSON.parseObject(payload.toString(), FeatherMessage.class);
        log.info("in FeatherReceiveService consume message success :[{}] ",JSON.toJSONString(payload));
    }
}
