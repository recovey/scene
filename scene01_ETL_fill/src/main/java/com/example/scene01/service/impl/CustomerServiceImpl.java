package com.example.scene01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scene01.dao.CustomerMapper;
import com.example.scene01.pojo.Customer;
import com.example.scene01.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {


    @Override
    public void init() {
        long startTime = System.currentTimeMillis(); //获取开始时间
        for (int i = 1; i < 200000; i++) {
            Customer c = new Customer();
            c.setName(i + "");
            super.save(c);
        }
        // saveBatch()
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间  程序运行时间：1126187ms
    }

    @Override
    public void init2() {
        List<Customer> customerList = new ArrayList<>();
        for (int i = 1; i < 200000; i++) {
            Customer c = new Customer();
            c.setName(i + "号顾客");
            customerList.add(c);
        }
        long startTime = System.currentTimeMillis(); //获取开始时间
        super.saveBatch(customerList);
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime)/1000 + "s"); //输出程序运行时间  程序运行时间：1126187ms
    }
}
