package org.feather.ecommerce.conf;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.conf
 * @className: SentinelConfig
 * @author: feather
 * @description: 开启服务间的调用保护，需要给RestTemplate 做一些包装
 * @since: 08-Oct-23 9:34 PM
 * @version: 1.0
 */
@Slf4j
@Configuration
public class SentinelConfig {

    @Bean
    @SentinelRestTemplate(
            fallback = "handleFallback" ,
            fallbackClass = RestTemplateExceptionUtil.class,
            blockHandler = "handleBlock",
            blockHandlerClass = RestTemplateExceptionUtil.class
    )
    public RestTemplate restTemplate(){
        //可以对其做一些业务相关的配置
        return  new RestTemplate();
    }


}
