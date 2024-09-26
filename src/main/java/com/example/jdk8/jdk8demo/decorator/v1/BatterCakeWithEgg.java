package com.example.jdk8.jdk8demo.decorator.v1;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2024/9/24
 * @since 3.0.1
 */
public class BatterCakeWithEgg extends BatterCake {

    @Override
    public String desc() {
        return super.desc() + "加";
    }

    @Override
    public int cost() {
        return super.cost();
    }
}
