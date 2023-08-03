package org.feather.ecommerce.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.filter
 * @className: HeaderTokenGateWayFilter
 * @author: feather
 * @description: 请求头部携带token 验证过滤器
 * @since: 2023-08-01 21:49
 * @version: 1.0
 */

public class HeaderTokenGateWayFilter  implements GatewayFilter , Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 从 HTTP Header 中寻找 key 为 token, value 为 feather 的键值对
        String name = exchange.getRequest().getHeaders().getFirst("token");
        if ("feather".equals(name)) {
            return chain.filter(exchange);
        }

        // 标记此次请求没有权限, 并结束这次请求
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 2;
    }
}
