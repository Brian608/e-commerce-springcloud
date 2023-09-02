package org.feather.ecommerce.service.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.feather.ecommerce.service.NacosClientService;
import org.springframework.cloud.client.ServiceInstance;

import java.util.Collections;
import java.util.List;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service.hystrix
 * @className: CacheHystrixCommand
 * @author: feather
 * @description: 带有缓存的功能的 Hystrix
 * @since: 2023-08-26 10:09
 * @version: 1.0
 */
@Slf4j
public class CacheHystrixCommand extends HystrixCommand<List<ServiceInstance>> {
    /**
     * 需要保护的服务
     */
    private  final NacosClientService nacosClientService;

    /**
     * 方法需要传递的参数
     */
    private final String serviceId;

    private static  final HystrixCommandKey CACHED_KEY= HystrixCommandKey.Factory.asKey("CacheHystrixCommand");

    public CacheHystrixCommand(NacosClientService nacosClientService,String serviceId) {
        super(
                HystrixCommand.Setter
                        .withGroupKey(HystrixCommandGroupKey
                                .Factory.asKey("CacheHystrixCommandGroup"))
                        .andCommandKey(CACHED_KEY)
        );
        this.nacosClientService=nacosClientService;
        this.serviceId=serviceId;
    }

    @Override
    protected List<ServiceInstance> run() throws Exception {
        log.info("CacheHystrixCommand In Hystrix Command to get service instance:" +
                " [{}], [{}]", this.serviceId, Thread.currentThread().getName());
        return this.nacosClientService.getNacosClientInfo(this.serviceId);
    }

    @Override
    protected List<ServiceInstance> getFallback() {
        return Collections.emptyList();
    }

    @Override
    protected String getCacheKey() {
        return serviceId;
    }

    /**
     * <h2>根据缓存 key 清理在一次 Hystrix 请求上下文中的缓存</h2>
     * */
    public static void flushRequestCache(String serviceId) {

        HystrixRequestCache.getInstance(
                CACHED_KEY,
                HystrixConcurrencyStrategyDefault.getInstance()
        ).clear(serviceId);
        log.info("flush request cache in hystrix command: [{}], [{}]",
                serviceId, Thread.currentThread().getName());
    }
}
