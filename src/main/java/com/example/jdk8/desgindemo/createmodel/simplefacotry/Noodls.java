package com.example.jdk8.desgindemo.createmodel.simplefacotry;

import lombok.Data;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/9/11 14:11
 * @Version 1.0
 */
@Data
public class Noodls extends Food {

    @Override
    public void addSpicy(String name) {
        System.out.println("面条" + name);

    }

    @Override
    public void addCondiment(String name) {

    }
}
