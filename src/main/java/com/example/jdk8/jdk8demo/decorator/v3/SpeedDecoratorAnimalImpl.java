package com.example.jdk8.jdk8demo.decorator.v3;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2024/9/26
 * @since 3.0.1
 */
public class SpeedDecoratorAnimalImpl extends AbsDecoratorAnimal {
    public SpeedDecoratorAnimalImpl(Animal animal) {
        super(animal);
    }

    @Override
    public void beforeMove() {
        System.out.println("speedBefore");
    }

    @Override
    public void afterMove() {
        System.out.println("speedAfter");
    }

    @Override
    public void beforeBite() {
        System.out.println("speedBeforeBite");
    }

    @Override
    public void afterBite() {
        System.out.println("speedAfterBite");
    }
}
