package com.example.recalccustomer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.recalccustomer.mapper.CustomerConfigMapper;
import com.example.recalccustomer.pojo.CustomerConfig;
import com.example.recalccustomer.service.CustomerConfigService;
import org.springframework.stereotype.Service;

@Service
public class CustomerConfigServiceImpl extends ServiceImpl<CustomerConfigMapper, CustomerConfig> implements CustomerConfigService {
}
