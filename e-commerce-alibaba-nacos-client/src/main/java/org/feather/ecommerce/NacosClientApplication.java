package org.feather.ecommerce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce
 * @className: NacosClientApplication
 * @author: feather
 * @description: TODO
 * @since: 2023-07-09 22:28
 * @version: 1.0
 */
@EnableFeignClients
@RefreshScope
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class NacosClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosClientApplication.class,args);
    }
}
