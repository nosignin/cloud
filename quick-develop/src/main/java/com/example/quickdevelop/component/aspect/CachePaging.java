package com.example.quickdevelop.component.aspect;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface CachePaging {

    String keyPrefix();

    Class clazz();

    int expire() default 30;//默认缓存30分钟

}
