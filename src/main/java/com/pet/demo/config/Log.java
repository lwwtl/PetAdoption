package com.pet.demo.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//元注解 元注解的作用就是负责注解其他注解
//@Target说明了Annotation所修饰的对象范围
//@Retention定义了该Annotation被保留的时间长短
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value() default "";
}
