package org.feather.ecommerce.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.conf
 * @className: GatewayBeanConf
 * @author: feather
 * @description:网关需要注入到容器中的bean
 * @since: 2023-08-03 22:11
 * @version: 1.0
 */
@Configuration
public class GatewayBeanConf {

    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }

}
