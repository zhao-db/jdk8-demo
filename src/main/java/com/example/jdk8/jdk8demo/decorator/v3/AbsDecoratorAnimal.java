package com.example.jdk8.jdk8demo.decorator.v3;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2024/9/26
 * @since 3.0.1
 */
public abstract class AbsDecoratorAnimal implements Animal {

    private Animal animal;

    public AbsDecoratorAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void move() {
        beforeMove();
        animal.move();
        afterMove();
    }

    public abstract void beforeMove();

    public abstract void afterMove();

    public abstract void beforeBite();

    public abstract void afterBite();

    @Override
    public void bite() {
        beforeBite();
        animal.bite();
        afterBite();
    }
}
