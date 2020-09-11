package com.example.jdk8.jdk8demo.annotation;

import sun.reflect.Reflection;

import java.lang.annotation.Annotation;
import java.lang.ref.Reference;
import java.lang.reflect.Method;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/9/7 14:06
 * @Version 1.0
 */
public class TestAnno {


    @MethodName(name = "zdb", desc = "测试注解")
    public String getAnnto() throws ClassNotFoundException {
        Method[] methods = Class.forName("com.example.jdk8.jdk8demo.annotation.TestAnno").getMethods();
        Annotation[] an = methods[1].getDeclaredAnnotations();
        for (Annotation a : an) {
            System.out.println(a.annotationType());
            System.out.println(a.toString());
            System.out.println();
        }
        Annotation[] an1 = methods[1].getAnnotations();
        for (Annotation a : an1) {
            System.out.println(a.annotationType());
            System.out.println(a.toString());
            System.out.println(a.equals("MethodName"));
        }


        return "";
    }

    public static void main(String[] args) throws ClassNotFoundException {
        TestAnno t = new TestAnno();
        t.getAnnto();
    }
}
