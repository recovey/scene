package com.example.recalccustomer.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dto.CustomerFinDto;
import com.example.recalccustomer.mapper.CustomerMapper;
import com.example.recalccustomer.pojo.Customer;
import com.example.recalccustomer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Map<Long, CustomerFinDto> findFinInfo(List<Long> ids) {
        return customerMapper.findFinInfo(ids);
    }
}
