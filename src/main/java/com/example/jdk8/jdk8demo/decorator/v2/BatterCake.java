package com.example.jdk8.jdk8demo.decorator.v2;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2024/9/24
 * @since 3.0.1
 */
public class BatterCake extends AbstractBatterCake{

    public String desc() {
        return "煎饼";
    }

    public int cost() {
        return 8;
    }
}
