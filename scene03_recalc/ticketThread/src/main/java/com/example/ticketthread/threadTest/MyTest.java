package com.example.ticketthread.threadTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MyTest {
    public static void main(String[] args) {
        //1:创建线程池对象
        ExecutorService pool = Executors.newFixedThreadPool(10, new ThreadFactory() {
            int id = 10;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "线程" + id++);
            }
        });

        //2:创建两个任务并提交
        for (int i = 1; i <=1000 ; i++) {
            MyTask myTask = new MyTask(i);
            pool.submit(myTask);
        }
        //3:关闭线程池
        pool.shutdown();
    }
}
