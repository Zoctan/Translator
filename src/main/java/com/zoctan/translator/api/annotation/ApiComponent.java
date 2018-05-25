package com.zoctan.translator.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * API注解
 * 需要用到的API必须注解才能被API工厂获取并实例化
 * 使用方式：直接在类上添加 @ApiComponent(name = "xx")
 *
 * @author Zoctan
 * @date 2018/5/25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ApiComponent {
    String name();
}
