package org.feather.ecommerce.service.communication.hystrix;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.service.communication.AuthorityFeignClient;
import org.feather.ecommerce.vo.JwtToken;
import org.feather.ecommerce.vo.UserNameAndPassword;
import org.springframework.stereotype.Component;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service.communication.hystrix
 * @className: AuthorityFeignClientFallbackFactory
 * @author: feather
 * @description:  <h1>OpenFeign 集成 Hystrix 的另一种模式</h1>
 * @since: 2023-09-02 20:48
 * @version: 1.0
 */

@Slf4j
@Component
public class AuthorityFeignClientFallbackFactory
        implements FallbackFactory<AuthorityFeignClient> {

    @Override
    public AuthorityFeignClient create(Throwable throwable) {

        log.warn("authority feign client get token by feign request error " +
                "(Hystrix FallbackFactory): [{}]", throwable.getMessage(), throwable);

        return new AuthorityFeignClient() {

            @Override
            public JwtToken getTokenByFeign(UserNameAndPassword userNameAndPassword) {
                return new JwtToken("feather-factory");
            }
        };
    }
}

