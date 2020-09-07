package com.example.jdk8.jdk8demo.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface Name {

    String originate();

    String community();

}
