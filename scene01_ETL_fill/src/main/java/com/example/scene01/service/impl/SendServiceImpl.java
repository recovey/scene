package com.example.scene01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scene01.dao.SendMapper;
import com.example.scene01.pojo.MqSend;
import com.example.scene01.pojo.Send;
import com.example.scene01.service.CustomerService;
import com.example.scene01.service.MqSendService;
import com.example.scene01.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SendServiceImpl extends ServiceImpl<SendMapper, Send> implements SendService {

    @Autowired
    private MqSendService mqSendService;

    @Autowired
    private CustomerService customerService;

    // 10->20000 20->10000  50->4000
    static final int PAGE_SIZE = 500;
    static final int TOTAL = 20000;
    // 插入20万条数据 PAGE_SIZE=10,程序运行时间：783s
    // 插入2万条数据 PAGE_SIZE=500,程序运行时间： 11s  12s  11s
    // 插入2万条数据 PAGE_SIZE=1000,程序运行时间：10s  12s  11s
    // 插入2万条数据 PAGE_SIZE=2000,程序运行时间：12s  11s  12s

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public void TestB1() {
        List<Send> SendList = new ArrayList<>();
        long startTime = System.currentTimeMillis(); //获取开始时间
        int current = 1;
        while (current <= TOTAL/PAGE_SIZE) {
            // PageSize=20 ，分页查询10 000次每次查询20条，共查到数据200 000,添加10 000条数据
            // PageSize=10 ，分页查询20 000次每次查询20条，共查到数据400 000,添加20 000条数据
            Page<MqSend> PageInfo = new Page<>(current, PAGE_SIZE);
            mqSendService.page(PageInfo);
            PageInfo.getRecords().forEach(mqSend -> {
                Send send = new Send();
                String custName = redisTemplate.opsForValue().get("mqSend"+mqSend.getCustomerId());
                if (custName!=null)
                    send.setCustomerName(custName);
               else{
                    custName = customerService.getById(mqSend.getCustomerId()).getName();
                    send.setCustomerName(custName);
                    redisTemplate.opsForValue().set("mqSend"+mqSend.getCustomerId(),custName,5, TimeUnit.MINUTES); // ,5, TimeUnit.MINUTES
               }
                SendList.add(send);
            });
            current ++;
        }
        super.saveBatch(SendList);
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime)  + "ms"); //输出程序运行时间  2万条 程序运行时间：65s
        // 分页大小500，填充数据20000，第一次程序运行时间：65116ms 第二次 31351ms 第三次 24367ms 第四次 23867ms 第五次 34188ms
        // 第一次76347ms 32935ms
        // 第一次52136ms 25479ms
    }
}
