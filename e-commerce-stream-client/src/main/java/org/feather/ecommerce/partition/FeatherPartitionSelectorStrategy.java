package org.feather.ecommerce.partition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.binder.PartitionSelectorStrategy;
import org.springframework.stereotype.Component;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.partition
 * @className: FeatherPartitionSelectorStrategy
 * @author: feather
 * @description: 决定message 发送到那个分区的策略
 * @since: 2023-09-06 22:14
 * @version: 1.0
 */
@Slf4j
@Component
public class FeatherPartitionSelectorStrategy implements PartitionSelectorStrategy {
    /**
     * <h2>选择分区的策略</h2>
     * */
    @Override
    public int selectPartition(Object key, int partitionCount) {

        int partition = key.toString().hashCode() % partitionCount;
        log.info("SpringCloud Stream feather Selector info: [{}], [{}], [{}]",
                key, partitionCount, partition);
        return partition;
    }
}
