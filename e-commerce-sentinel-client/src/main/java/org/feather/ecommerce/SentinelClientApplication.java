package org.feather.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce
 * @className: SentinelClientApplication
 * @author: feather
 * @description:
 * @since: 07-Oct-23 9:42 PM
 * @version: 1.0
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SentinelClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelClientApplication.class,args);
    }
}
