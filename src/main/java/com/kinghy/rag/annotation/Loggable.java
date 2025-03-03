package com.kinghy.rag.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  // 注解作用在方法上
@Retention(RetentionPolicy.RUNTIME)  // 运行时生效
public @interface Loggable {
    String value() default "";  // 可选参数，例如操作描述
}
