package com.example.scene01;

import com.example.scene01.thread.MyThread;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ThreadTest {

    @Test
    void TestA1(){
        MyThread my1 = new MyThread();
        MyThread my2 = new MyThread();

    }
}
