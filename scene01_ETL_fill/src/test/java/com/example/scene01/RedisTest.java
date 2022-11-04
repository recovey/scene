package com.example.scene01;

import com.example.scene01.pojo.Customer;
import com.example.scene01.service.CustomerService;
import com.example.scene01.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
//    @Test
//    void TestC1(){
//        Customer customer = new Customer(1, "你还好吗");
//        redisTemplate.opsForValue().set("customer"+customer.getId(),customer);
//    }

//    @Autowired
//    private RedisUtils redisUtils;

    @Test
    void TestC2(){
//        System.out.println(redisTemplate.opsForValue().get("你好"));
//        System.out.println(redisUtils.get("你好"));
        System.out.println(redisTemplate.opsForValue().get("customer1"));
    }
//    @SuppressWarnings("unchecked")
//    @Autowired
//    private CustomerService customerService;
}
