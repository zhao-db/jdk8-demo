package com.example.jdk8.desgindemo.createmodel.factory;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/9/11 14:31
 * @Version 1.0
 */
public class AmericaFoodFacotry implements FoodFactory {

    @Override
    public Food makeFood(String name) {
        return new AmericaFood();
    }
}
