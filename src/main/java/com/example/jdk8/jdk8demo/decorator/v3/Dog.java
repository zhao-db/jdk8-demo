package com.example.jdk8.jdk8demo.decorator.v3;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2024/9/26
 * @since 3.0.1
 */
public class Dog implements Animal {
    @Override
    public void move() {
        System.out.println("狗跑");
    }

    @Override
    public void bite() {
        System.out.println("狗叫");
    }
}
