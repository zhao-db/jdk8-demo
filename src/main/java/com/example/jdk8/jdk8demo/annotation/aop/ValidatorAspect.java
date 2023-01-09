package com.example.jdk8.jdk8demo.annotation.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.example.jdk8.jdk8demo.annotation.NumberCheck;
import com.example.jdk8.jdk8demo.annotation.ValidatorInteger;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2023/1/6
 * @since 3.0.1
 */
@Aspect
@Component
public class ValidatorAspect {

    @Pointcut("@annotation(com.example.jdk8.jdk8demo.annotation.ValidatorInteger)")
    public void validator() {
    }

    @Before("validator()")
    public void preHandler(JoinPoint joinPoint) {
        Object aThis = joinPoint.getThis();
        Signature signature = joinPoint.getSignature();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("request = " + JSON.toJSONString(request.getParameterMap()));
    }

    @Around("validator()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (parameterAnnotations == null || parameterAnnotations.length == 0) {
            return joinPoint.proceed();
        }
        Object[] paramValues = joinPoint.getArgs();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (int j = 0; j < parameterAnnotations[i].length; j++) {
                if (parameterAnnotations[i][j] != null && parameterAnnotations[i][j] instanceof NumberCheck) {
                    int paramValue = ((int) paramValues[i]);
                    if (paramValue < 0 || paramValue > 100) {
                        throw new RuntimeException("请输入正确的年龄");
                    }
                }
            }
        }
        return joinPoint.proceed();
    }
}
