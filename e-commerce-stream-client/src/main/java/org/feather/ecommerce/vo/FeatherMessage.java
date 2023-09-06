package org.feather.ecommerce.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.vo
 * @className: FeatherMessage
 * @author: feather
 * @description: 消息传递对象  springCloud Stream + kafka /rocketmq
 * @since: 2023-09-05 21:58
 * @version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeatherMessage {
    private Integer id;
    private String projectName;
    private String org;
    private String author;
    private String version;
    /**
     * 返回一个默认的消息，方便使用
     * @return
     */
    public static FeatherMessage defaultMessage() {

        return new FeatherMessage(
                1,
                "e-commerce-stream-client",
                "feather",
                "feather",
                "1.0"
        );
    }

}
