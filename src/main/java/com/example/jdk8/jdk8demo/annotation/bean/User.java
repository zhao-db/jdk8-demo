package com.example.jdk8.jdk8demo.annotation.bean;

import lombok.Data;

import com.example.jdk8.jdk8demo.annotation.NumberCheck;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/2/8
 * @since 3.0.1
 */
@Data
public class User {

    private Integer id;

    private String name;

    @NumberCheck
    private Integer age;

    private String nickName;

}
