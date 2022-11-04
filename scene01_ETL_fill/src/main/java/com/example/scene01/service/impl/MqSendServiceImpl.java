package com.example.scene01.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scene01.dao.MqSendMapper;
import com.example.scene01.pojo.Customer;
import com.example.scene01.pojo.MqSend;
import com.example.scene01.pojo.Send;
import com.example.scene01.service.MqSendService;
import com.example.scene01.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MqSendServiceImpl extends ServiceImpl<MqSendMapper, MqSend> implements MqSendService {


    @Override
    public void TestA1() {
        List<MqSend> mqSendList = new ArrayList<>();
        for (int i = 1; i < 200000; i++) {
            MqSend mqSend = new MqSend();
            mqSend.setCustomerId(i);
            mqSendList.add(mqSend);
        }
        long startTime = System.currentTimeMillis(); //获取开始时间
        super.saveBatch(mqSendList);
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime)/1000 + "s"); //输出程序运行时间
        //  10万数据程序运行时间：8110ms
        // 100万数据程序运行时间：43407ms
        // 500万数据程序运行时间：208758ms
    }

    static int start = 100000;
    static int pageSize = 1000;

    @Override
    public void TestA2() {
        for (int i = 0; i < start / 10000; i++) {
            Page<MqSend> mqSendPage = new Page<>(start, pageSize);
            long startTime = System.currentTimeMillis(); //获取开始时间
            super.page(mqSendPage);
            long endTime = System.currentTimeMillis(); //获取结束时间
            System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
        }
    }

    @Override
    public void TestA3() {
        for (int i = 10000; i < start; ) {
            long startTime = System.currentTimeMillis(); //获取开始时间
            super.getById(i);
            long endTime = System.currentTimeMillis(); //获取结束时间
            System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
            i += 10000;
        }
    }
}
