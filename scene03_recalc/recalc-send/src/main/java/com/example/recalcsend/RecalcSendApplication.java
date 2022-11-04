package com.example.recalcsend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// 3、声明扫描远程接口（Feigh接口）所在的包,openFeign会负责实例化这些接口的实现
@EnableFeignClients("com.example.recalcsend.api")
@EnableDiscoveryClient
public class RecalcSendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecalcSendApplication.class, args);
    }

}
