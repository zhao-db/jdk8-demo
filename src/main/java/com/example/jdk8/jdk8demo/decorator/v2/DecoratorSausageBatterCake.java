package com.example.jdk8.jdk8demo.decorator.v2;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2024/9/26
 * @since 3.0.1
 */
public class DecoratorSausageBatterCake extends AbsDecoratorBatterCake {

    private AbstractBatterCake abstractBatterCake;

    public DecoratorSausageBatterCake(AbstractBatterCake abstractBatterCake) {
        this.abstractBatterCake = abstractBatterCake;
    }

    @Override
    public String desc() {
        return abstractBatterCake.desc() + "加肠";
    }

    @Override
    public int cost() {
        return abstractBatterCake.cost() + 1;
    }
}
