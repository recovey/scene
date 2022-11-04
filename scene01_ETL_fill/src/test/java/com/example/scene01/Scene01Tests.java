package com.example.scene01;

import com.example.scene01.pojo.MqSend;
import com.example.scene01.service.CustomerService;
import com.example.scene01.service.MqSendService;
import com.example.scene01.service.SendService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Scene01Tests {

    @Autowired
    CustomerService customerService;

    @Autowired
    MqSendService mqSendService;

    @Test
    void init1(){
        // 完成对customer表的初始化（单条添加10w条数据） 用时1126s
        customerService.init();
    }

    @Test
    void init2(){
        // 完成对customer表的初始化（批量添加20w条数据） 用时12s
        customerService.init2();
    }

    @Test
    void TestA1(){
        mqSendService.TestA1();
    }

    @Test
    void TestA2(){
        mqSendService.TestA2();
    }

    @Test
    void TestA3(){
        mqSendService.TestA3();
    }


    @Autowired
    private SendService sendService;

    @Test
    void TestB1(){
        sendService.TestB1();
    }
}
