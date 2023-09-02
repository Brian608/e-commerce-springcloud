package org.feather.ecommerce.service.communication;

import org.feather.ecommerce.service.communication.hystrix.AuthorityFeignClientFallBack;
import org.feather.ecommerce.service.communication.hystrix.AuthorityFeignClientFallbackFactory;
import org.feather.ecommerce.vo.JwtToken;
import org.feather.ecommerce.vo.UserNameAndPassword;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service.communication
 * @className: AuthorityFeignClient
 * @author: feather
 * @description:   与Authority 服务通信的Feign Client 接口定义
 * @since: 2023-08-20 10:13
 * @version: 1.0
 */
@FeignClient(contextId = "AuthorityFeignClient",value = "e-commerce-authority-center",
       // fallback = AuthorityFeignClientFallBack.class
        fallbackFactory = AuthorityFeignClientFallbackFactory.class)
public interface AuthorityFeignClient {

    /**
     * <h2>通过 OpenFeign 访问 Authority 获取 Token</h2>
     * */
    @RequestMapping(value = "/ecommerce-authority-center/authority/token",
            method = RequestMethod.POST,
            consumes = "application/json",produces = "application/json")
    JwtToken getTokenByFeign(@RequestBody UserNameAndPassword userNameAndPassword);


}
