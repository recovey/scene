package com.example.recalccustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// 启动nacos服务注册与发现功能
@EnableDiscoveryClient
// 3、声明扫描远程接口（Feigh接口）所在的包,openFeign会负责实例化这些接口的实现
@EnableFeignClients("com.example.recalccustomer.api")
public class RecalcCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecalcCustomerApplication.class, args);
    }

}
