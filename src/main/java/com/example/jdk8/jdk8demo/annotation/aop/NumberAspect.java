package com.example.jdk8.jdk8demo.annotation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.example.jdk8.jdk8demo.annotation.utils.NumberValidator;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/2/8
 * @since 3.0.1
 */
@Aspect
@Component
public class NumberAspect {

    @Pointcut("@annotation(com.example.jdk8.jdk8demo.annotation.NumberCheck)")
    public void checkNumber() {
    }

    @Before("checkNumber()")
    public void preHandle(JoinPoint joinPoint) throws IllegalAccessException {
        Class<? extends JoinPoint> aClass = joinPoint.getClass();
        NumberValidator.validator(aClass);
    }
}
