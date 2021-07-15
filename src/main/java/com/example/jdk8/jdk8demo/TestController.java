package com.example.jdk8.jdk8demo;

import com.example.jdk8.jdk8demo.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Collin
 * @date 2021/4/22 3:00 下午
 */
@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    private Map<String, ServiceTest> serviceTestMap;
   @RequestMapping("/test")
    public String sss(){
       StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,ServiceTest> m : serviceTestMap.entrySet()){
            System.out.println(m.getValue());
            System.out.println(m.getKey());
            sb.append(m.getKey() +":"+ m.getValue());
        }

       return sb.toString();
   }

}
