package org.feather.ecommerce.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.config
 * @className: DynamicRouteServiceImpl
 * @author: feather
 * @description: 事件推送  aware ：动态更新 路由网关 Service
 * @since: 2023-07-16 16:51
 * @version: 1.0
 */
@Slf4j
@Service
@SuppressWarnings("all")
public class DynamicRouteServiceImpl   implements ApplicationEventPublisherAware {
    /** 写路由定义 */
    private final RouteDefinitionWriter routeDefinitionWriter;
    /** 获取路由定义 */
    private final RouteDefinitionLocator routeDefinitionLocator;

    /** 事件发布 */
    private ApplicationEventPublisher publisher;

    public DynamicRouteServiceImpl(RouteDefinitionWriter routeDefinitionWriter,
                                   RouteDefinitionLocator routeDefinitionLocator) {
        this.routeDefinitionWriter = routeDefinitionWriter;
        this.routeDefinitionLocator = routeDefinitionLocator;
    }

    @Override
    public void setApplicationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher) {
        // 完成事件推送句柄的初始化
        this.publisher = applicationEventPublisher;
    }

    /**
     * <h2>增加路由定义</h2>
     * */
    public String addRouteDefinition(RouteDefinition definition) {

        log.info("gateway add route: [{}]", definition);

        // 保存路由配置并发布
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        // 发布事件通知给 Gateway, 同步新增的路由定义
        this.publisher.publishEvent(new RefreshRoutesEvent(this));

        return "success";
    }

    /**
     * <h2>更新路由</h2>
     * */
    public String updateList(List<RouteDefinition> definitions) {

        log.info("gateway update route: [{}]", definitions);

        // 先拿到当前 Gateway 中存储的路由定义
        List<RouteDefinition> routeDefinitionsExits =
                routeDefinitionLocator.getRouteDefinitions().buffer().blockFirst();
        if (!CollectionUtils.isEmpty(routeDefinitionsExits)) {
            // 清除掉之前所有的 "旧的" 路由定义
            routeDefinitionsExits.forEach(rd -> {
                log.info("delete route definition: [{}]", rd);
                deleteById(rd.getId());
            });
        }

        // 把更新的路由定义同步到 gateway 中
        definitions.forEach(definition -> updateByRouteDefinition(definition));
        return "success";
    }

    /**
     * <h2>根据路由 id 删除路由配置</h2>
     * */
    private String deleteById(String id) {

        try {
            log.info("gateway delete route id: [{}]", id);
            this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
            // 发布事件通知给 gateway 更新路由定义
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return "delete success";
        } catch (Exception ex) {
            log.error("gateway delete route fail: [{}]", ex.getMessage(), ex);
            return "delete fail";
        }
    }

    /**
     * <h2>更新路由</h2>
     * 更新的实现策略比较简单: 删除 + 新增 = 更新
     * */
    private String updateByRouteDefinition(RouteDefinition definition) {

        try {
            log.info("gateway update route: [{}]", definition);
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
        } catch (Exception ex) {
            return "update fail, not find route routeId: " + definition.getId();
        }

        try {
            this.routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return "success";
        } catch (Exception ex) {
            return "update route fail";
        }
    }

}
