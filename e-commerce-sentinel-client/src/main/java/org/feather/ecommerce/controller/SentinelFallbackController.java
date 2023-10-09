package org.feather.ecommerce.controller;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.bolck_handler.FeatherBlockHandler;
import org.feather.ecommerce.fallback_handler.FeatherFallBackHandler;
import org.feather.ecommerce.vo.JwtToken;
import org.feather.ecommerce.vo.UserNameAndPassword;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.controller
 * @className: SentinelFallbackController
 * @author: feather
 * @description: 提供容错降级的功能
 * @since: 09-Oct-23 8:50 PM
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/sentinel-fallback")
public class SentinelFallbackController {

    /**
     * 注入没有增强的RestTemplate
     */
    private final RestTemplate restTemplate;


    public SentinelFallbackController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 从 授权 服务中获取jwtToken
     1. 流控降级:
     * 是针对于簇点链路中的 http://127.0.0.1:7000/ecommerce-authority-center/authority/token
     * 2. 容错降级: 对于服务不可用时不能生效
     * @param userNameAndPassword
     * @return
     */
    @PostMapping("/get-token")
    @SentinelResource(
                  value = "getTokenFromAuthorityService",
            fallback = "getTokenFromAuthorityServiceFallback",
            fallbackClass = {FeatherFallBackHandler.class}
            )
    public JwtToken getTokenFromAuthorityService(
            @RequestBody UserNameAndPassword userNameAndPassword
    ){
        String requestUrl =
                "http://127.0.0.1:7000/ecommerce-authority-center/authority/token";
        log.info("RestTemplate request url and body: [{}], [{}]",
                requestUrl, JSON.toJSONString(userNameAndPassword));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.postForObject(
                requestUrl,
                new HttpEntity<>(JSON.toJSONString(userNameAndPassword), headers),
                JwtToken.class
        );
    }

    /**
     * 让 Sentinel 忽略一些异常
     * @param code
     * @return
     */
    @GetMapping("/ignore-exception")
    @SentinelResource(
            value = "ignoreException",
            fallback = "ignoreExceptionFallback",
            fallbackClass = { FeatherFallBackHandler.class },
            exceptionsToIgnore = { NullPointerException.class }
    )
    public JwtToken ignoreException(@RequestParam Integer code) {

        if (code % 2 == 0) {
            throw new NullPointerException("you input code is: " + code);
        }

        return new JwtToken("feather-token");
    }

}
