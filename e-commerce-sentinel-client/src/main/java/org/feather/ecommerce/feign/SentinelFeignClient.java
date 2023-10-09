package org.feather.ecommerce.feign;

import org.feather.ecommerce.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.feign
 * @className: SentinelFeignClient
 * @author: feather
 * @description: 通过sentinel 对openFeign 实现熔断降级
 * @since: 09-Oct-23 9:20 PM
 * @version: 1.0
 */
@FeignClient(
        value = "e-commerce-feather",
        fallback = SentinelFeignClientFallBack.class
)
@Component
public interface SentinelFeignClient {

    @RequestMapping(value = "/feather", method = RequestMethod.GET)
    CommonResponse<String> getResultByFeign(@RequestParam Integer code);
}

