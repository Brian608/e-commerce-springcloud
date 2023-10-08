package org.feather.ecommerce.controller;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.bolckHandler.FeatherBlockHandler;
import org.feather.ecommerce.vo.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.controller
 * @className: RateLimitController
 * @author: feather
 * @description: 基于sentinel 控制台配置流控规则
 * sentinel 是懒加载的，先去访问一下，就可以在sentinel Dashboard 看到了
 * @since: 08-Oct-23 8:28 PM
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/dashboard")
public class RateLimitController {

    /**
     * 在dashboard 中 "流控规则" 中按照资源名称新增流控规则
     * @return
     */
    @GetMapping("/by-resource")
    @SentinelResource(
            value = "byResource",
            blockHandler = "featherHandleBlockException",
            blockHandlerClass = FeatherBlockHandler.class
    )
    public CommonResponse<String> byResource(){
        log.info("coming in rate limit controller by resource ");
        return new CommonResponse<>(0,"","byResource");
    }


    /**
     * 在  "蔟点链路" 中给url新增流控规则
     * @return
     */
    @GetMapping("/by-url")
    @SentinelResource(
            value = "byUrl",
            blockHandler = "featherHandleBlockException",
            blockHandlerClass = FeatherBlockHandler.class
    )
    public CommonResponse<String> byUrl(){
        log.info("coming in rate limit controller by url ");
        return new CommonResponse<>(0,"","byUrl");
    }

}
