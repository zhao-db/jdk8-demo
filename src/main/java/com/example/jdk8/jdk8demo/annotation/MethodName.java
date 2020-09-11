package com.example.jdk8.jdk8demo.annotation;

import java.lang.annotation.*;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/9/7 16:17
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodName {

    String name();

    String desc();
}
