package org.feather.ecommerce.conf;

import org.feather.ecommerce.filter.LoginUserInfoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.conf
 * @className: FeatherWebMvcConfig
 * @author: feather
 * @description: web mvc 配置，
 * @since: 2023-08-13 11:14
 * @version: 1.0
 */
@Configuration
public class FeatherWebMvcConfig  extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        //添加用户统身份统一登录的拦截器
        registry.addInterceptor(new LoginUserInfoInterceptor()).
                addPathPatterns("/**")
                .order(0);
    }

    /**
     * 让 mvc 加载swagger的静态资源
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath://META-INF/resources/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath://META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}
