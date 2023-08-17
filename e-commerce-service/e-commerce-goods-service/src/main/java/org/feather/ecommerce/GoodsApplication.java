package org.feather.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce
 * @className: GoogsApplication
 * @author: feather
 * @description:  启动依赖组件  redis  mysql nacos kafka zipkin
 * @since: 2023-08-16 22:03
 * @version: 1.0
 */
@EnableJpaAuditing
@EnableDiscoveryClient
@SpringBootApplication
public class GoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class,args);
    }
}
