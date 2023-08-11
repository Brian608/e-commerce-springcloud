package org.feather.ecommerce.sampler;

import brave.sampler.RateLimitingSampler;
import brave.sampler.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.sampler
 * @className: SamplerConfig
 * @author: feather
 * @description:使用配置的方式设定采样率  (代码配置和配置文件配置只能一种)
 * @since: 2023-08-11 8:33
 * @version: 1.0
 */
@Configuration
public class SamplerConfig {
    /**
     * <h2>限速采集</h2>
     * */
    @Bean
    public Sampler sampler() {
        return RateLimitingSampler.create(100);
    }

//    /**
//     * <h2>概率采集, 默认的采样策略, 默认值是 0.1</h2>
//     * */
//    @Bean
//    public Sampler defaultSampler() {
//        return ProbabilityBasedSampler.create(0.5f);
//    }
}
