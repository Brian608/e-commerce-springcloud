package org.feather.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce
 * @className: AuthorityCenterApplication
 * @author: feather
 * @description: TODO
 * @since: 2023-07-13 22:25
 * @version: 1.0
 */
@EnableJpaAuditing
@EnableDiscoveryClient
@SpringBootApplication
public class AuthorityCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorityCenterApplication.class,args);
    }
}
