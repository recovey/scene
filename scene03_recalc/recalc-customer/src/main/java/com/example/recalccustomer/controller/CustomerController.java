package com.example.recalccustomer.controller;

import Vo.Result;
import com.example.recalccustomer.pojo.Customer;
import com.example.recalccustomer.pojo.CustomerConfig;
import com.example.recalccustomer.service.CustomerConfigService;
import com.example.recalccustomer.service.CustomerService;
import dto.CustomerFinDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/findByFinObjIds")
    public Result<Map<Long, CustomerFinDto>> findFinInfo(@RequestBody List<Long> ids) {
        System.out.println("" + ids);
        Map<Long, CustomerFinDto> dtoMap = customerService.findFinInfo(ids);
        return new Result<Map<Long, CustomerFinDto>>().ok(dtoMap);
    }

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private CustomerConfigService customerConfigService;

    @GetMapping("csh")
    String findXiShu(){
        HashMap<String, Double> map = new HashMap<>();
        for (int i = 90000; i <= 100000; i++) {
            CustomerConfig customerConfig = customerConfigService.getById(i);
            map.put(i+"",customerConfig.getWeightRate());
        }
        redisTemplate.opsForValue().set("XiShu2",map);
        return "加载完毕";
    }

    @GetMapping("csh2")
    String findXiShu2(){
        HashMap<String, Double> map = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            CustomerConfig customerConfig = customerConfigService.getById(i);
            map.put(i+"",customerConfig.getWeightRate());
        }
        redisTemplate.opsForValue().set("XiShu3",map);
        return "加载完毕";
    }
}
