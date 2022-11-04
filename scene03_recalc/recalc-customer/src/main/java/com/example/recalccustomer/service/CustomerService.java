package com.example.recalccustomer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.recalccustomer.pojo.Customer;
import dto.CustomerFinDto;

import java.util.List;
import java.util.Map;

public interface CustomerService extends IService<Customer> {
    Map<Long, CustomerFinDto> findFinInfo(List<Long> ids);
}
