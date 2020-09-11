package com.example.jdk8.desgindemo.createmodel.factory;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/9/11 14:32
 * @Version 1.0
 */
public class TestMain {

    public static void main(String[] args) {
        FoodFactory food = new ChineseFoodFacotry();
        Food food1 = food.makeFood("sdf");
        FoodFactory usaFood = new AmericaFoodFacotry();
        Food food2 = usaFood.makeFood("sdf");
        System.out.println(food1.getClass());
        System.out.println(food2.getClass());
    }

}
