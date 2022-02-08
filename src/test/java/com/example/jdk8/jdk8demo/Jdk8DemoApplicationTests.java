package com.example.jdk8.jdk8demo;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class Jdk8DemoApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {
        String body = "{\"deviceTypeId\":1,\"deviceModelId\":1,\"deviceCode\":\"2\",\"warehouseCode\":\"1\",\"deviceName\":\"光栅门2\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        for (int i = 3; i < 100; i++) {
            JSONObject js = new JSONObject();
            js.put("deviceTypeId", 1);
            js.put("deviceModelId", 1);
            js.put("deviceCode", i);
            js.put("warehouseCode", 1);
            js.put("deviceName", "光栅门" + i);
            HttpEntity httpEntity = new HttpEntity(js, headers);
            String url = "http://localhost/venus/v3/device/info/add";
            ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        }
    }

}
