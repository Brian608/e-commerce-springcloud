package org.feather.ecommerce;

import org.feather.ecommerce.conf.DataSourceProxyAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce
 * @className: LogisticsApplication
 * @author: feather
 * @description:
 * @since: 25-Sep-23 10:14 PM
 * @version: 1.0
 */
@Import(DataSourceProxyAutoConfiguration.class)
@EnableAspectJAutoProxy
@EnableDiscoveryClient
@SpringBootApplication
public class LogisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogisticsApplication.class,args);
    }

}
