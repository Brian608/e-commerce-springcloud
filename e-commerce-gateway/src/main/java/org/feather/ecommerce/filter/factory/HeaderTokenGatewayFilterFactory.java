package org.feather.ecommerce.filter.factory;

import org.feather.ecommerce.filter.HeaderTokenGateWayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.filter.factory
 * @className: HeaderTokenAbstractGatewayFilterFactory
 * @author: feather
 * @description:
 * @since: 2023-08-01 21:55
 * @version: 1.0
 */
@Component
public class HeaderTokenGatewayFilterFactory
        extends AbstractGatewayFilterFactory<Object> {
    @Override
    public GatewayFilter apply(Object config) {
        return  new HeaderTokenGateWayFilter();
    }
}
