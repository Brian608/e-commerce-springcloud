package org.feather.ecommerce.service.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.service.NacosClientService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service.hystrix
 * @className: CacheHystrixCommandAnnotation
 * @author: feather
 * @description: 使用注解方式开启Hystrix 请求缓存
 * @since: 2023-08-27 14:47
 * @version: 1.0
 */
@Slf4j
@Service
public class CacheHystrixCommandAnnotation {

    private final NacosClientService nacosClientService;

    public  CacheHystrixCommandAnnotation (NacosClientService nacosClientService){
        this.nacosClientService=nacosClientService;
    }

    /**
     * 第一种，Hystrix Cache 注解的使用方法
     * @param serviceId
     * @return
     */
    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand(commandKey = "CacheHystrixCommandAnnotation")
    public List<ServiceInstance> useCacheByAnnotation01(String serviceId){
        log.info("use cache01 to get nacos client info :[{}]",serviceId);
        return nacosClientService.getNacosClientInfo(serviceId);
    }

    @CacheRemove(commandKey = "CacheHystrixCommandAnnotation",cacheKeyMethod ="getCacheKey" )
    @HystrixCommand
    public  void flushCacheByAnnotation01(String cacheId){
        log.info("flush hystrix cache key :[{}] ",cacheId);
    }

    public String getCacheKey(String cacheId){
        return cacheId;
    }

    /**
     * 第二种 Hystrix Cache 注解的使用方法
     * @param serviceId
     * @return
     */
    @CacheResult
    @HystrixCommand(commandKey = "CacheHystrixCommandAnnotation")
    public List<ServiceInstance> useCacheByAnnotation02(@CacheKey String serviceId) {

        log.info("use cache02 to get nacos client info: [{}]", serviceId);
        return nacosClientService.getNacosClientInfo(serviceId);
    }
    @CacheRemove(commandKey = "CacheHystrixCommandAnnotation")
    @HystrixCommand
    public void flushCacheByAnnotation02(@CacheKey String cacheId) {
        log.info("flush hystrix cache key: [{}]", cacheId);
    }

    /**
     * 第三种 Hystrix Cache 注解的使用方法
     * @param serviceId
     * @return
     */
    @CacheResult
    @HystrixCommand(commandKey = "CacheHystrixCommandAnnotation")
    public List<ServiceInstance> useCacheByAnnotation03(String serviceId) {

        log.info("use cache03 to get nacos client info: [{}]", serviceId);
        return nacosClientService.getNacosClientInfo(serviceId);
    }

    @CacheRemove(commandKey = "CacheHystrixCommandAnnotation")
    @HystrixCommand
    public void flushCacheByAnnotation03(String cacheId) {
        log.info("flush hystrix cache key: [{}]", cacheId);
    }

}
