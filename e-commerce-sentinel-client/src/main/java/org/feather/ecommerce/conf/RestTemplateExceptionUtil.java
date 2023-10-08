package org.feather.ecommerce.conf;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.vo.JwtToken;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.conf
 * @className: RestTemplateExceptionUtil
 * @author: feather
 * @description: restTemplate 在限流或异常的兜底方法
 * @since: 08-Oct-23 9:44 PM
 * @version: 1.0
 */
@Slf4j
public class RestTemplateExceptionUtil {

    /**
     * 限流后的处理方法
     * @param request
     * @param body
     * @param execution
     * @param ex
     * @return
     */
    public static SentinelClientHttpResponse handleBlock(HttpRequest request,
                                                        byte [] body,
                                                        ClientHttpRequestExecution execution,
                                                        BlockException ex) {

        log.error("Handle RestTemplate Block Exception: [{}], [{}]",
                request.getURI().getPath(), ex.getClass().getCanonicalName());
        return new SentinelClientHttpResponse(
                JSON.toJSONString(new JwtToken("feather-block"))
        );
    }

    /**
     * 异常降级之后的处理方法
     * @param request
     * @param body
     * @param execution
     * @param ex
     * @return
     */

    public  static  SentinelClientHttpResponse handleFallback(
            HttpRequest request,
            byte [] body,
            ClientHttpRequestExecution execution,
            BlockException ex
    ){
        log.error("Handle RestTemplate Fallback Exception: [{}], [{}]",
                request.getURI().getPath(), ex.getClass().getCanonicalName());
        return new SentinelClientHttpResponse(
                JSON.toJSONString(new JwtToken("feather-block"))
        );
    }

}
