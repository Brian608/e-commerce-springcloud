package org.feather.ecommerce.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.annotation
 * @className: IgnoreResponseAdvice
 * @author: feather
 * @description: 忽略统一响应注解定义
 * @since: 2023-07-09 22:22
 * @version: 1.0
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseAdvice {
}