package org.feather.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.feather.ecommerce.service.communication.AuthorityFeignClient;
import org.feather.ecommerce.service.communication.UseFeignApi;
import org.feather.ecommerce.service.communication.UseRibbonService;
import org.feather.ecommerce.service.communication.UserRestTemplateService;
import org.feather.ecommerce.vo.JwtToken;
import org.feather.ecommerce.vo.UserNameAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.controller
 * @className: communicationController
 * @author: feather
 * @description:
 * @since: 2023-08-19 11:41
 * @version: 1.0
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/communication")
@RestController
public class CommunicationController {

    private  final UserRestTemplateService userRestTemplateService;

    private final UseRibbonService ribbonService;

    private final AuthorityFeignClient feignClient;

    private final UseFeignApi useFeignApi;

    @PostMapping("/rest-template")
    public JwtToken getTokenFromAuthorityService(
            @RequestBody UserNameAndPassword userNameAndPassword) {
        return userRestTemplateService.getTokenFromAuthorityService(userNameAndPassword);
    }


    @PostMapping("/rest-template-load-balancer")
    public JwtToken getTokenFromAuthorityServiceWithLoadBalancer(
            @RequestBody UserNameAndPassword userNameAndPassword) {
        return userRestTemplateService.getTokenFromAuthorityServiceWithLoadBalance(userNameAndPassword);
    }


    @PostMapping("/ribbon")
    public JwtToken getTokenFromAuthorityServiceByRibbon(
            @RequestBody UserNameAndPassword userNameAndPassword) {
        return ribbonService.getTokenFromAuthorityServiceByRibbon(userNameAndPassword);
    }

    @PostMapping("/thinking-in-ribbon")
    public JwtToken thinkingInRibbon(@RequestBody UserNameAndPassword userNameAndPassword) {
        return ribbonService.thinkingInRibbon(userNameAndPassword);
    }

    @PostMapping("/token-by-feign")
    public JwtToken getTokenByFeign(@RequestBody UserNameAndPassword userNameAndPassword) {
        return feignClient.getTokenByFeign(userNameAndPassword);
    }

    @PostMapping("/thinking-in-feign")
    public JwtToken thinkingInFeign(@RequestBody UserNameAndPassword userNameAndPassword) {
        return useFeignApi.thinkingInFeign(userNameAndPassword);
    }

}
