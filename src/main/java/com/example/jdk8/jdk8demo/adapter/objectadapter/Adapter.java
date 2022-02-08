package com.example.jdk8.jdk8demo.adapter.objectadapter;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2021/10/27
 * @since 3.0.1
 */
public class Adapter implements Target {

    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        adaptee.adapteeRequest();
    }
}
