package com.example.scene01;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest
public class JedisTest {

    @Test
    void TestA1(){
        Jedis jedis = new Jedis("localhost", 6666);  //指定Redis服务Host和port
//        jedis.auth("xxxx"); //如果Redis服务连接需要密码，制定密码
        long startTime = System.currentTimeMillis(); //获取开始时间
        for (int i = 1; i <= 10; i++)
            jedis.set("key"+i,"value"+i);
        jedis.close(); //使用完关闭连接
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("插入数据，程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间 46ms
    }

    @Test
    void TestA2(){
        Jedis jedis = new Jedis("localhost", 6666);  //指定Redis服务Host和port

        for (int j = 0; j < 10; j++) {
            long startTime2 = System.currentTimeMillis(); //获取开始时间
            for (int i = 1; i <= 10; i++)
                jedis.get("value"+i);
            jedis.close(); //使用完关闭连接
            long endTime2 = System.currentTimeMillis(); //获取结束时间
            System.out.println("读取数据，程序运行时间：" + (endTime2 - startTime2) + "ms"); //输出程序运行时间 51ms
        }
    }
}
