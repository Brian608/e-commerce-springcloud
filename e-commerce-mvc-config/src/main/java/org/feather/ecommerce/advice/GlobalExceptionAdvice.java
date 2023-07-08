package org.feather.ecommerce.advice;

import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.advice
 * @className: GlobalExceptionAdvice
 * @author: feather
 * @description: 全局异常捕获处理
 * @since: 2023-07-08 17:29
 * @version: 1.0
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    public CommonResponse<String> handlerCommerceException(HttpServletRequest req, Exception ex) {

        CommonResponse<String> response = new CommonResponse<>(
                -1, "business error"
        );
        response.setData(ex.getMessage());
        log.error("commerce service has error: [{}]", ex.getMessage(), ex);
        return response;
    }
}
