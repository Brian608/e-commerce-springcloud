package org.feather.ecommerce;

import org.feather.ecommerce.conf.DataSourceProxyAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce
 * @className: OpderApplication
 * @author: feather
 * @description:
 * @since: 2023-09-10 20:06
 * @version: 1.0
 */
@EnableCircuitBreaker //保证openfeign 开启Hystrix
@EnableDiscoveryClient
@EnableJpaAuditing
@EnableFeignClients
@SpringBootApplication
@Import(DataSourceProxyAutoConfiguration.class)
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
