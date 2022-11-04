package com.example.scene01.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyCallable mc = new MyCallable();

        //因为 MyCallable 实现了 String 类型的 Callable 接口
        //所以返回值也是 String 类型，所以创建的是 String 类型的 FutureTask 对象
        FutureTask<String> ft = new FutureTask<>(mc);

        //传入 FutureTask 实例，创建线程对象
        Thread t1 = new Thread(ft);

        //不能在这个地方使用 FutureTask 的 get 方法获取异步线程的返回值，否则程序将卡死在这里。
        //因为 t1 线程还没有执行，所以无法获取到返回值，所以如果执行 get 方法，程序将卡死在这里。
        //String s = ft.get();

        //开启新线程，异步执行 MyCallable 实例中的 call 方法逻辑
        t1.start();

        //这里编写一些实现其它业务逻辑代码进行执行
        //可以做一些其它比较耗时的任务
        //......

        //获取异步线程的返回值
        String s = ft.get();
        System.out.println(s);
    }
}