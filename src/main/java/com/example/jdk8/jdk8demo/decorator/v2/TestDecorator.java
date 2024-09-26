package com.example.jdk8.jdk8demo.decorator.v2;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2024/9/26
 * @since 3.0.1
 */
public class TestDecorator {
    public static void main(String[] args) {
        AbstractBatterCake abstractBatterCake;
        abstractBatterCake = new BatterCake();
        abstractBatterCake = new DecoratorEggBatterCake(abstractBatterCake);
        abstractBatterCake = new DecoratorSausageBatterCake(abstractBatterCake);
        System.out.println(abstractBatterCake.desc() + " : " + abstractBatterCake.cost());
    }
}
