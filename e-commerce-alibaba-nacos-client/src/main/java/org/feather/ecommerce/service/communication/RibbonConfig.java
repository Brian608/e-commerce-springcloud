package org.feather.ecommerce.service.communication;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service.communication
 * @className: RibbonConfig
 * @author: feather
 * @description: 使用 Ribbon 之前的配置, 增强 RestTemplate
 * @since: 2023-08-20 8:26
 * @version: 1.0
 */
@Component
public class RibbonConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
