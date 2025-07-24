package com.example.jdk8.jdk8demo.enums;

import lombok.Getter;

@Getter
public enum ColorEnum {

    BLACK("black", "黑色"),
    RED("red", "红色"),
    WHITE("white", "白色"),
    YELLOW("yellow", "黄色");

    ColorEnum(String code, String color) {
        this.code = code;
        this.color = color;
    }

    private String color;
    private String code;

}
