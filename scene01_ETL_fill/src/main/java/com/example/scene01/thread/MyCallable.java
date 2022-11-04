package com.example.scene01.thread;

import java.util.concurrent.Callable;
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }

        //这里返回一个字符串
        return "这是我返回的字符串结果";
    }
}



