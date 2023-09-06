package org.feather.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce
 * @className: StreamClientApplication
 * @author: feather
 * @description: 基于springcloud Stream 构建消息驱动微服务
 * @since: 2023-09-05 21:57
 * @version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StreamClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamClientApplication.class,args);
    }

}
