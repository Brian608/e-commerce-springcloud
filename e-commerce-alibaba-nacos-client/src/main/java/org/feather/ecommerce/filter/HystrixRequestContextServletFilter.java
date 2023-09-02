package org.feather.ecommerce.filter;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.filter
 * @className: HystrixRequestContextServletFilter
 * @author: feather
 * @description: 初始化 Hystrix 请求上下文环境
 * @since: 2023-08-26 9:35
 * @version: 1.0
 */
@WebFilter(
        filterName = "HystrixRequestContextServletFilter",
        urlPatterns = "/*",
        asyncSupported = true
)
@Component
@Slf4j
public class HystrixRequestContextServletFilter  implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        //初始化 Hystrix 请求上下文
        //在不同的context 中缓存是不共享的
        //这个初始化是必须的
        HystrixRequestContext context=HystrixRequestContext.initializeContext();
        try {
            // 配置
            hystrixConcurrencyStrategyConfig();
            // 请求正常通过
            filterChain.doFilter(request, response);
        } finally {
            // 关闭 Hystrix 请求上下文
            context.shutdown();
        }

    }

    /**
     * 配置 Hystrix 的 并发策略
     */
    public  void  hystrixConcurrencyStrategyConfig(){
        try {
            HystrixConcurrencyStrategy target =
                    HystrixConcurrencyStrategyDefault.getInstance();
            HystrixConcurrencyStrategy strategy =
                    HystrixPlugins.getInstance().getConcurrencyStrategy();
            if (strategy instanceof HystrixConcurrencyStrategyDefault) {
                // 如果已经就是我们想要配置的
                return;
            }
            // 将原来其他的配置保存下来
            HystrixCommandExecutionHook commandExecutionHook =
                    HystrixPlugins.getInstance().getCommandExecutionHook();
            HystrixEventNotifier eventNotifier =
                    HystrixPlugins.getInstance().getEventNotifier();
            HystrixMetricsPublisher metricsPublisher =
                    HystrixPlugins.getInstance().getMetricsPublisher();
            HystrixPropertiesStrategy propertiesStrategy =
                    HystrixPlugins.getInstance().getPropertiesStrategy();

            // 先重置, 再把我们自定义的配置与原来的配置写回去
            HystrixPlugins.reset();
            HystrixPlugins.getInstance().registerConcurrencyStrategy(target);
            HystrixPlugins.getInstance().registerCommandExecutionHook(commandExecutionHook);
            HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);
            HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
            HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);

            log.info("config hystrix concurrency strategy success");

        }catch (Exception e){
            log.error("Failed to register Hystrix Concurrency Strategy :[{}]",e.getMessage(),e);
        }
    }
}
