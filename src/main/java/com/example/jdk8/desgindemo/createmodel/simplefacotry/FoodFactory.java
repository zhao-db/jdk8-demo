package com.example.jdk8.desgindemo.createmodel.simplefacotry;

/**
 * @Description 简单工厂模式
 * @Author zhaodb
 * @Date 2020/9/11 14:10
 * @Version 1.0
 */
public class FoodFactory {

    public static Food makeFood(String name) {
        if (name.equals("noodels")) {
            Food noodls = new Noodls();
            noodls.addSpicy("more");
            System.out.println(noodls.getClass());
            return noodls;
        }
        if (name.equals("chicken")) {
            Food chichen = new Chichen();
            chichen.addCondiment("tolalsdf");
            System.out.println(chichen.getClass());
            return chichen;
        }
        return null;
    }

    public static void main(String[] args) {
        makeFood("chicken");
        makeFood("noodels");
    }

}
