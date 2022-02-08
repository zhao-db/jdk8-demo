package com.example.jdk8.jdk8demo.adapter.classadapter;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2021/10/27
 * @since 3.0.1
 */
public class Test {

    public static void main(String[] args) {
        Target target = new ConcreteTarget();
        target.request();
        Target adapterTarget = new Adapter();
        adapterTarget.request();
    }
}
