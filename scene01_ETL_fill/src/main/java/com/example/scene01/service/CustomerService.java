package com.example.scene01.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.scene01.pojo.Customer;

public interface CustomerService  extends IService<Customer> {

    void init();

    void init2();
}
