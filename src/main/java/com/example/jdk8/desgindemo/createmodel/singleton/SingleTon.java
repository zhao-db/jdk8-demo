package com.example.jdk8.desgindemo.createmodel.singleton;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/9/11 15:38
 * @Version 1.0
 */
public class SingleTon {

    private SingleTon() {

    }

    private static SingleTon singleTon = new SingleTon();

    public static SingleTon getInstance() {
        return singleTon;
    }

}
