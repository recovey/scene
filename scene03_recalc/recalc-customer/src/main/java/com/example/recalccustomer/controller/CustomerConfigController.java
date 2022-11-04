package com.example.recalccustomer.controller;

import Vo.Result;
import com.example.recalccustomer.api.SendService;
import com.example.recalccustomer.pojo.CustomerConfig;
import com.example.recalccustomer.service.CustomerConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerConfigController {
    @Autowired
    SendService sendService;

    @Autowired
    CustomerConfigService configService;

    @GetMapping("/abc")
    Result<Object> getMsg(){
        return sendService.getMsg();
    }

    @GetMapping("/dates")
    Result<Object> getList(){
        return sendService.getList();
    }

    @GetMapping("/xishu/{id}")
    double getxishu(@PathVariable("id") Long id){
        CustomerConfig config = configService.getById(id);
        return config.getWeightRate();
    }

}
