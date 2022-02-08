package com.example.jdk8.jdk8demo.enums;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RpcMode {

    DUBBO("dubbo"),
    MOTAN("motan");

    private String id;

}
