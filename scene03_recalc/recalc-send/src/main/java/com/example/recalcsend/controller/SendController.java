package com.example.recalcsend.controller;


import Vo.Result;
import com.example.recalcsend.api.CustomerConfigService;
import com.example.recalcsend.service.SendService;
import dto.CustomerFinDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class SendController {

    @Autowired
    private SendService sendService;

    @Autowired
    CustomerConfigService customerConfigService;

    @GetMapping("/abc")
    Result<Object> getMsg() {
        return new Result<>().ok("123");
    }

    @GetMapping("/dates")
    Result<Object> getList() {
        return new Result<>().ok(sendService.getByScanTime("2021-10-01 00:00:00", "2021-10-3 00:00:00"));
    }

    @GetMapping("/volume")
    String setVolumeWeight() {
        return sendService.computerVolume() ? "计算成功" : "计算失败";
    }

    @GetMapping("volumePage")
    String computerVolumePage() {
        return sendService.computerVolumePage() ? "计算成功" : "计算失败";
    }

    @GetMapping("volumePage2")
    String computerVolumePage2() {
        return sendService.computerVolumePage2() ? "计算成功" : "计算失败";
    }

    @Autowired
    RedisTemplate<String,Object> redisTemplate;


    @GetMapping("test")
    Object TestA1() {
//        Object xiShu = redisTemplate.opsForHash().get("XiShu", 95306+"");
        HashMap<String, Double>  xiShu= (HashMap<String, Double>) redisTemplate.opsForValue().get("XiShu2");
        System.out.println(xiShu);
        return xiShu;
    }
}

