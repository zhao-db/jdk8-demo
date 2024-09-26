package com.example.jdk8.jdk8demo.decorator.v3;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2024/9/26
 * @since 3.0.1
 */
public class TestMain {
    public static void main(String[] args) {
        Animal animal;
        animal = new Dog();
        animal.move();
        animal.bite();

        animal = new SpeedDecoratorAnimalImpl(animal);
        animal.move();
        animal.bite();

        animal = new HighDecoratorAnimalImpl(animal);
        animal.move();
        animal.bite();

    }
}
