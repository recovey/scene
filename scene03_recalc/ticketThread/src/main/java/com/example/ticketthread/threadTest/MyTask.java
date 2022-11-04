package com.example.ticketthread.threadTest;

import com.example.ticketthread.service.TicketService;
import com.example.ticketthread.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class MyTask implements Runnable {
    //用户姓名
    private String userName;
    //取款金额
    private double money;
    //总金额
    private static double total = 1000;

    public MyTask(String userName, double money) {
        this.userName = userName;
        this.money = money;
    }

    private int id;
    public MyTask(int id){
        this.id = id;
    }
//    @Autowired  不能注入，只能new啊啊
//    TicketServiceU ticketService;

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
//        System.out.println(userName+"正在准备使用"+name+"取款:"+money+"元");
        System.out.println(name+"线程，正在服务"+id);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (MyTask.class){
//            if(total-money>0){
//                System.out.println(userName+"使用"+name+"取款:"+money+"元成功,余额:"+(total-money));
//                total-=money;
//            }else {
//                System.out.println(userName+"使用"+name+"取款:"+money+"元失败,余额:"+total);
//            }
            new TicketServiceImpl().rob(id);
        }
    }
}
