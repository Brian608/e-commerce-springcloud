package org.feather.ecommerce;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce
 * @className: AdminApplication
 * @author: feather
 * @description: 监控中心
 * @since: 2023-07-12 21:20
 * @version: 1.0
 */
@EnableAdminServer
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
