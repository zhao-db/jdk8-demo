package com.example.jdk8.jdk8demo.adapter.objectadapter;

import com.example.jdk8.jdk8demo.adapter.classadapter.Target;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2021/10/27
 * @since 3.0.1
 */
public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("ConcreteTarget 目标方法");
    }
}
