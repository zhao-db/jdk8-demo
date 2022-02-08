package com.example.jdk8.jdk8demo.annotation;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/2/8
 * @since 3.0.1
 */

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface NumberCheck {

    int value() default 0;

}
