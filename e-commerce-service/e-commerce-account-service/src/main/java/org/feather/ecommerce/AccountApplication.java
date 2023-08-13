package org.feather.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce
 * @className: AccountApplication
 * @author: feather
 * @description:
 * @since: 2023-08-13 16:09
 * @version: 1.0
 */
@EnableJpaAuditing
@SpringBootApplication
@EnableDiscoveryClient
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class,args);
    }
}
