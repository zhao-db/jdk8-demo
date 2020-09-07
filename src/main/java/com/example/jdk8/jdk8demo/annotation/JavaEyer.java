package com.example.jdk8.jdk8demo.annotation;

@Description("javaeye,做最棒的软件开发交流社区")
public class JavaEyer {
    @Name(originate = "创始人:robbin", community = "javaEye")
    public String getName() {
        return null;
    }

    @Name(originate = "创始人:江南白衣", community = "springside")
    public String getName2() {
        return "借用两位的id一用,写这一个例子,请见谅!";
    }


    public static void main(String[] args) {
        JavaEyer jav = new JavaEyer();
        System.out.println(jav.getName());
        System.out.println(jav.getName2());

    }

}