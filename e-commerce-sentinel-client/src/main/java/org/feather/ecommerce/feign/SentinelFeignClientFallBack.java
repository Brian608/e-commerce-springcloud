package org.feather.ecommerce.feign;

import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.vo.CommonResponse;
import org.springframework.stereotype.Component;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.feign
 * @className: SentinelFeignClientFallBack
 * @author: feather
 * @description: sentinel 对 OpenFeign 接口降级策略
 * @since: 09-Oct-23 9:24 PM
 * @version: 1.0
 */
@Slf4j
@Component
public class SentinelFeignClientFallBack implements SentinelFeignClient{
    @Override
    public CommonResponse<String> getResultByFeign(Integer code) {
        log.error( "request  supply for test has some error :[{}]",code);
        return new CommonResponse<>(-1,"sentinel feign fallback","input code:"+code);
    }
}
