package com.example.jdk8;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/11/24 15:19
 * @Version 1.0
 */
public class TestReflect {

    private String name;

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestReflect{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
