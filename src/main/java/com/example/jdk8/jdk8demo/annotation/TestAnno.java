package com.example.jdk8.jdk8demo.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.example.jdk8.jdk8demo.annotation.bean.User;
import com.example.jdk8.jdk8demo.annotation.utils.NumberValidator;

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

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {
/*        TestAnno t = new TestAnno();
        t.getAnnto();*/

        User user = new User();
        user.setAge(111);
        user.setId(1);
        user.setName("zhaodb");
        user.setNickName("sdfjkl");
        System.out.println(user.toString());
        NumberValidator.validator(user);

    }
}
