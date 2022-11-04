package com.example.scene01;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 声明式的事务配置
@MapperScan("com.example.scene01.dao")
public class Scene01Application {
    public static void main(String[] args) {
        SpringApplication.run(Scene01Application.class, args);
    }
}
