package org.feather.ecommerce.service.hystrix;


import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.service.NacosClientService;
import org.springframework.cloud.client.ServiceInstance;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import org.springframework.cloud.client.ServiceInstance;

import java.util.Collections;
import java.util.List;

import static com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy.THREAD;


/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service.hystrix
 * @className: NacosClientHystricCommand
 * @author: feather
 * @description:  给nacos client 实现包装
 * hystrix  舱壁模式
 * 1：线程池
 * 2：信号量  算法 + 数据结构  有限状态机
 * @since: 2023-08-22 8:31
 * @version: 1.0
 */
@Slf4j
public class NacosClientHystrixCommand extends HystrixCommand<List<ServiceInstance>> {

    private final NacosClientService nacosClientService;

    /**
     * 方法需要传递的参数
     */
    private  final String serviceId;

    public NacosClientHystrixCommand(NacosClientService nacosClientService, String serviceId) {
        super(
                Setter.withGroupKey(
                                HystrixCommandGroupKey.Factory.asKey("NacosClientService"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("NacosClientHystrixCommand"))
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("NacosClientPool"))
                        // 线程池 key 配置
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withExecutionIsolationStrategy(THREAD) // 线程池隔离策略
                                        .withFallbackEnabled(true)  // 开启降级
                                        .withCircuitBreakerEnabled(true)    // 开启熔断器
                        )
        );

        // 可以配置信号量隔离策略
//        Setter semaphore =
//                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("NacosClientService"))
//                .andCommandKey(HystrixCommandKey.Factory.asKey("NacosClientHystrixCommand"))
//                .andCommandPropertiesDefaults(
//                        HystrixCommandProperties.Setter()
//                        .withCircuitBreakerRequestVolumeThreshold(10)
//                        .withCircuitBreakerSleepWindowInMilliseconds(5000)
//                        .withCircuitBreakerErrorThresholdPercentage(50)
//                        .withExecutionIsolationStrategy(SEMAPHORE)  // 指定使用信号量隔离
//                        //.....
//                );
       this.nacosClientService=nacosClientService;
       this.serviceId=serviceId;
    }

    /**
     * 要保护的方法  写在run 方法中
     * @return
     * @throws Exception
     */
    @Override
    protected List<ServiceInstance> run() throws Exception {
        log.info("NacosClientService In Hystrix Command to Get Service Instance: [{}], [{}]",
                this.serviceId, Thread.currentThread().getName());
        return this.nacosClientService.getNacosClientInfo(this.serviceId);
    }

    /**
     * 降级处理策略
     * @return
     */
    @Override
    protected List<ServiceInstance> getFallback() {
        log.warn("NacosClientService run error: [{}], [{}]",
                this.serviceId, Thread.currentThread().getName());
        return Collections.emptyList();
    }
}
