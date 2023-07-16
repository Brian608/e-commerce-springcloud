package org.feather.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce
 * @className: GateWayApplication
 * @author: feather
 * @description: 网关启动入口
 * @since: 2023-07-16 14:53
 * @version: 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class,args);
    }

}
