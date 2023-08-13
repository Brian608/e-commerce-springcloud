package org.feather.ecommerce.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.conf
 * @className: SwaggerConfig
 * @author: feather
 * @description:swagger 配置类
 * @since: 2023-08-13 13:42
 * @version: 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * <h2>Swagger 实例 Bean 是 Docket, 所以通过配置 Docket 实例来配置 Swagger</h2>
     * */
    @Bean
    public Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2)
                // 展示在 Swagger 页面上的自定义工程描述信息
                .apiInfo(apiInfo())
                // 选择展示哪些接口
                .select()
                // 只有 org.feather.ecommerce 包内的才去展示
                .apis(RequestHandlerSelectors.basePackage("org.feather.ecommerce"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * <h2>Swagger 的描述信息</h2>
     * */
    public ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("feather-micro-service")
                .description("e-commerce-springcloud-service")
                .contact(new Contact(
                        "feather", "org.feather", "xxxx.com"
                ))
                .version("1.0")
                .build();
    }

}
