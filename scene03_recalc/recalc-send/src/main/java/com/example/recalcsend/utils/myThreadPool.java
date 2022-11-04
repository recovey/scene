package com.example.recalcsend.utils;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class myThreadPool {
    // 一个线程最大处理数据量
    private static final int THREAD_COUNT_SIZE = 5000;

    public static void executeThreadPool(List<String> pmsProductList) {

        long start = System.currentTimeMillis();

        // 线程数，以5000条数据为一个线程，总数据大小除以5000，再加1
        int round = pmsProductList.size() / THREAD_COUNT_SIZE + 1;
        // 程序计数器
        final CountDownLatch count = new CountDownLatch(round);
        // 创建线程
        ExecutorService executor = Executors.newFixedThreadPool(round);
        // 分配数据
        for (int i = 0; i < round; i++) {
            int startLen = i * THREAD_COUNT_SIZE;
            int endLen = ((i + 1) * THREAD_COUNT_SIZE > pmsProductList.size() ? pmsProductList.size()
                    : (i + 1) * THREAD_COUNT_SIZE);
            final List<String> threadList = pmsProductList.subList(startLen, endLen);
            int k = i + 1;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    // 我这里用的是 jpa的直接添加一个list，当然这里可以把list，for循环然后调用
                    // 接口添加数据库,或者进行其他数据操作
                    System.out.println("正在处理线程【" + k + "】的数据，数据大小为：" + threadList.size());
                    // 计数器 -1(唤醒阻塞线程)
                    count.countDown();
                }
            });
        }
        try {
            // 阻塞线程(主线程等待所有子线程 一起执行业务)
            count.await();
            long end = System.currentTimeMillis();
            System.out.println("100万数据插入查询耗时:" + (end - start) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 终止线程池
            // 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。若已经关闭，则调用没有其他作用。
            executor.shutdown();
        }
    }
}
