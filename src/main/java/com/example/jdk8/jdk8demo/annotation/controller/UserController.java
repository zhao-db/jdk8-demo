package com.example.jdk8.jdk8demo.annotation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jdk8.jdk8demo.annotation.NumberCheck;
import com.example.jdk8.jdk8demo.annotation.ValidatorInteger;
import com.example.jdk8.jdk8demo.annotation.bean.User;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/2/8
 * @since 3.0.1
 */
@RestController
@RequestMapping("/test/anno/")
public class UserController {

    @RequestMapping("/userinfo")
    @ValidatorInteger
    public String userInfo(@RequestParam("age") @NumberCheck Integer age) {
        User user = new User();
        user.setAge(age);
        user.setId(1);
        user.setName("zhaodb");
        user.setNickName("sdfjkl");
        System.out.println();
        return user.toString();
    }

}
