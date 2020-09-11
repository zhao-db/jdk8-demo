package com.example.jdk8.desgindemo.createmodel.simplefacotry;

import lombok.Data;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/9/11 14:15
 * @Version 1.0
 */
@Data
public class Chichen extends Food {

    @Override
    public void addSpicy(String name) {

    }

    @Override
    public void addCondiment(String name) {
        System.out.println("鸡肉" + name);
    }
}
