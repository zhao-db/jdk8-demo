package com.example.jdk8.jdk8demo.config;

import com.example.jdk8.jdk8demo.enums.RpcMode;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.Annotation;

@Configuration
public class RPCModeConfig {

    public static class DUBBO implements Condition {
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            return (conditionContext.getEnvironment().getProperty("RPC.mode").equals(RpcMode.DUBBO.getId()));
        }
    }

    public static class MOTAN implements Condition {
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            return conditionContext.getEnvironment().getProperty("RPC.mode").equals(RpcMode.MOTAN.getId());
        }
    }

}
