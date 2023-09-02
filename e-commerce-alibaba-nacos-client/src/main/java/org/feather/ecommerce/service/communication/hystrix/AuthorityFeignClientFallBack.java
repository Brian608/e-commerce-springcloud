package org.feather.ecommerce.service.communication.hystrix;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.service.communication.AuthorityFeignClient;
import org.feather.ecommerce.vo.JwtToken;
import org.feather.ecommerce.vo.UserNameAndPassword;
import org.springframework.stereotype.Component;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service.communication.hystrix
 * @className: AuthorityFeignClientFallBack
 * @author: feather
 * @description:  AuthorityFeignClientFallBack 后备fallback
 * @since: 2023-09-02 20:31
 * @version: 1.0
 */
@Component
@Slf4j
public class AuthorityFeignClientFallBack  implements AuthorityFeignClient {

    @Override
    public JwtToken getTokenByFeign(UserNameAndPassword userNameAndPassword) {
        log.info("authority feign client get token by feign request error " +
                "(Hystrix Fallback): [{}]", JSON.toJSONString(userNameAndPassword));
        return new JwtToken("feather");
    }
}
