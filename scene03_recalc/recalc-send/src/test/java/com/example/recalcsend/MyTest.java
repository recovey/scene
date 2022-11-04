package com.example.recalcsend;

import com.example.recalcsend.utils.saveThread;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyTest {

    @Test
    void TestA1(){
//        for (int i = 0; i < 100; i++) {
//            saveThread thread = new saveThread();
//            thread.start();
//        }
        //创建线程
        saveThread thread1 = new saveThread();
        saveThread thread2 = new saveThread();
        saveThread thread3 = new saveThread();
        saveThread thread4 = new saveThread();
        saveThread thread5 = new saveThread();
        saveThread thread11 = new saveThread();
        saveThread thread22 = new saveThread();
        saveThread thread33 = new saveThread();
        saveThread thread44 = new saveThread();
        saveThread thread55 = new saveThread();
        //启动线程
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread11.start();
        thread22.start();
        thread33.start();
        thread44.start();
        thread55.start();

    }

    @Test
    void TestA(){
        saveThread s = new saveThread();
        s.start();
    }
}
