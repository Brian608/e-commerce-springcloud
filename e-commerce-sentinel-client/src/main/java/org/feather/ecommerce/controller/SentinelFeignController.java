package org.feather.ecommerce.controller;

import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.feign.SentinelFeignClient;
import org.feather.ecommerce.vo.CommonResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.controller
 * @className: SentinelFeignController
 * @author: feather
 * @description: OpenFeign  集成 Sentinel 实现熔断降级
 * @since: 09-Oct-23 9:28 PM
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/sentinel-feign")
public class SentinelFeignController {


    private final SentinelFeignClient sentinelFeignClient;

    public SentinelFeignController(SentinelFeignClient sentinelFeignClient) {
        this.sentinelFeignClient = sentinelFeignClient;
    }



    /**
     * 通过Feign 接口去获取结果
     * @param code
     * @return
     */
    @GetMapping("/result-by-feign")
    public CommonResponse<String> getResultByFeign (@RequestParam Integer code){
        log.info("coming in get result by feign :[{}]",code);
        return  sentinelFeignClient.getResultByFeign(code);
    }

}
