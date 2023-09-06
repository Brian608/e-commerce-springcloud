package org.feather.ecommerce.partition;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.vo.FeatherMessage;
import org.springframework.cloud.stream.binder.PartitionKeyExtractorStrategy;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.partition
 * @className: FeatherPartitionKeyExtractorStrategy
 * @author: feather
 * @description: 自定义从message 中提取 partition key 的策略
 * @since: 2023-09-06 22:11
 * @version: 1.0
 */
@Slf4j
@Component
public class FeatherPartitionKeyExtractorStrategy  implements PartitionKeyExtractorStrategy {

    @Override
    public Object extractKey(Message<?> message) {
        FeatherMessage featherMessage= JSON.parseObject(message.getPayload().toString(),FeatherMessage.class);
        //自定义提取key
        String key = featherMessage.getProjectName();
        log.info("SpringCloud Stream  Feather Partition key :[{}]",key);
        return key;
    }
}
