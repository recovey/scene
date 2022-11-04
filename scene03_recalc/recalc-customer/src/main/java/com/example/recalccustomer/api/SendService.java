package com.example.recalccustomer.api;

import Vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("recalc-send") // 1、声明该接口使用cloud-provider提供的服务
public interface SendService {

    @GetMapping("/dates")
    Result<Object> getList();

    @GetMapping("/abc")
    Result<Object> getMsg();
}
