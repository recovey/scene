package com.example.recalcsend.api;

import Vo.Result;
import dto.CustomerFinDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient("recalc-customer")
public interface CustomerConfigService {

    @GetMapping("/xishu/{id}")
    double getxishu(@PathVariable("id") Long id);

    @PostMapping("/findByFinObjIds")
    Result<Map<Long, CustomerFinDto>> findFinInfo(@RequestBody List<Long> ids);

}
