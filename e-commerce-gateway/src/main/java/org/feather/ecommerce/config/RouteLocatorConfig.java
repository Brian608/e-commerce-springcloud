package org.feather.ecommerce.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.config
 * @className: RouteLocatorConfig
 * @author: feather
 * @description:配置登录请求转发规则
 * @since: 2023-08-04 7:21
 * @version: 1.0
 */
@Configuration
public class RouteLocatorConfig {
    /**
     * 使用代码定义路由规则，在网关层面拦截下登录和注册接口
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator loginRouteLocator(RouteLocatorBuilder builder){
        // 手动定义 Gateway 路由规则需要指定 id、path 和 uri
        return builder.routes()
                .route(
                        "e_commerce_authority",
                        r -> r.path(
                                "/feather/e-commerce/login",
                                "/feather/e-commerce/register"
                        ).uri("http://localhost:9001/")
                ).build();
    }
}
