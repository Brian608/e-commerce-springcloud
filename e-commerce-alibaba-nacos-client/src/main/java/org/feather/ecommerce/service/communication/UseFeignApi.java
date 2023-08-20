package org.feather.ecommerce.service.communication;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.feather.ecommerce.vo.JwtToken;
import org.feather.ecommerce.vo.UserNameAndPassword;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Random;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service.communication
 * @className: UseFeignApi
 * @author: feather
 * @description:使用feign 的原生api
 * @since: 2023-08-20 16:29
 * @version: 1.0
 */
@Slf4j
@Service
public class UseFeignApi {

    private  final DiscoveryClient discoveryClient;

    public UseFeignApi(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    /**
     * 使用feign 的原生api调用远程服务
     * Feign 默认配置初始化，设置自定义配置，生成代理对象
     * @return
     */
    public JwtToken thinkingInFeign(UserNameAndPassword userNameAndPassword){
        //通过反射  去拿serviceId
        String serviceId=null;
        Annotation [] annotations=AuthorityFeignClient.class.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(FeignClient.class)){
                serviceId=((FeignClient)annotation).value();
                log.info("get service id from  AuthorityFeignClient :[{}] ",serviceId);
                break;
            }
        }
        //如果服务id不存在
        if (null==serviceId){
            throw  new RuntimeException("can not get serviceId");
        }
        //通过serviceId 去拿可用的服务实例
        List<ServiceInstance>  targetInstances=discoveryClient.getInstances(serviceId);
        if (CollectionUtils.isEmpty(targetInstances)){
            throw  new RuntimeException("can not ge target instance from serviceId: "+serviceId);
        }
        //随机选择一个服务实例
        ServiceInstance randomInstance=targetInstances.get(new Random().nextInt(targetInstances.size()));
        log.info("choose service instance: [{}], [{}], [{}]", serviceId,
                randomInstance.getHost(), randomInstance.getPort());
        // Feign 客户端初始化, 必须要配置 encoder、decoder、contract
        AuthorityFeignClient feignClient = Feign.builder()  // 1. Feign 默认配置初始化
                .encoder(new GsonEncoder())                 // 2.1 设置定义配置
                .decoder(new GsonDecoder())                 // 2.2 设置定义配置
                .logLevel(Logger.Level.FULL)                // 2.3 设置定义配置
                .contract(new SpringMvcContract())
                .target(                                    // 3 生成代理对象
                        AuthorityFeignClient.class,
                        String.format("http://%s:%s",
                                randomInstance.getHost(), randomInstance.getPort())
                );

        return feignClient.getTokenByFeign(userNameAndPassword);

    }
}
