package org.feather.ecommerce.service.communication;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.constants.CommonConstants;
import org.feather.ecommerce.vo.JwtToken;
import org.feather.ecommerce.vo.UserNameAndPassword;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service.communication
 * @className: UserRestTemplateService
 * @author: feather
 * @description:
 * @since: 2023-08-19 11:24
 * @version: 1.0
 */

@Service
@Slf4j
public class UserRestTemplateService {

    private final LoadBalancerClient loadBalancerClient;

    public UserRestTemplateService(LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
    }

    /**
     * 从授权中心获取jwttoken
     * @param userNameAndPassword
     * @return
     */
    public JwtToken getTokenFromAuthorityService(UserNameAndPassword userNameAndPassword){
        // 第一种方式: 写死 url
        String requestUrl = "http://127.0.0.1:7000/ecommerce-authority-center/authority/token";
        log.info("RestTemplate request url and body: [{}], [{}]",
                requestUrl, JSON.toJSONString(userNameAndPassword));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new RestTemplate().postForObject(
                requestUrl,
                new HttpEntity<>(JSON.toJSONString(userNameAndPassword), headers),
                JwtToken.class
        );
    }

    /**
     * 从授权服务中获取jwttoken 带有负载均衡
     * @param userNameAndPassword
     * @return
     */
    public JwtToken getTokenFromAuthorityServiceWithLoadBalance(UserNameAndPassword userNameAndPassword) {
        //第二种方式，通过注册中心拿到服务的信息(所有的实例) 再发起调用
        ServiceInstance serviceInstance = loadBalancerClient.choose(CommonConstants.AUTHORITY_CENTER_SERVICE_ID);
        log.info("Nacos Client Info: [{]],[{}],[{]]",serviceInstance.getServiceId(),serviceInstance.getInstanceId(),JSON.toJSONString(serviceInstance));
        String requestUrl=String.format("http://%s:%s/ecommerce-authority-center/authority/token",serviceInstance.getHost(),serviceInstance.getPort());
        log.info("login request url and body [{]],[{}]",requestUrl,JSON.toJSONString(userNameAndPassword));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new RestTemplate().postForObject(
                requestUrl,
                new HttpEntity<>(JSON.toJSONString(userNameAndPassword), headers),
                JwtToken.class
        );
    }



}
