package com.zoctan.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * API接口注解
 *
 * @author Zoctan
 * @date 2018/06/29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ApiComponent {
    /**
     * API名称
     */
    String name();
}
