package com.example.ticketthread.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TicketController {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @GetMapping("/saveRedis")
    String saveRedis(){
//        Map<Integer, Object> map = new HashMap<>();
//        for (int i = 0; i < 50; i++) map.put(i,"10元优惠卷");
//        redisTemplate.opsForHash().putAll("ticketAll",map);
//        return "生成成功";

//        HashMap<String, String> map = new HashMap<>();
//        for (int i = 1; i <= 10; i++) {
//            map.put(i+"","10元优惠卷");
//        }
//        redisTemplate.opsForValue().set("ticketAll",map);
//        return "优惠卷生成完毕";
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            hashMap.put((i+1)+"","10元优惠卷");  //不能用int Integer 会报类型无法装转的错误
        }
        redisTemplate.opsForHash().putAll("ticketHash",hashMap);
        return "ok";
    }

    @GetMapping("/getRedis")
    String getRedis(){
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, String> data = (HashMap<String, String>) redisTemplate.opsForHash().get("ticketHash", (i + 1) + "");
        }
        return "ok";
//        RedisOperations operations = new RedisOperations;
//        生成100张优惠卷放入redis中 -> 开1000个线程去抢 ->
    }
}
