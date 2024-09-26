package com.example.jdk8.jdk8demo.decorator.v3;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2024/9/26
 * @since 3.0.1
 */
public class HighDecoratorAnimalImpl extends AbsDecoratorAnimal {
    public HighDecoratorAnimalImpl(Animal animal) {
        super(animal);
    }

    @Override
    public void beforeMove() {
        System.out.println("highBefore");
    }

    @Override
    public void afterMove() {
        System.out.println("highAfter");
    }

    @Override
    public void beforeBite() {
        System.out.println("highBeforeBite");
    }

    @Override
    public void afterBite() {
        System.out.println("highAfterBite");
    }
}
