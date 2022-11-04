package com.example.ticketthread.threadTest;

import com.example.ticketthread.service.TicketService;
import com.example.ticketthread.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

public class TicketThread extends Thread {
    static int ticketNum = 10;

    @Resource
    TicketService ticketService;

    @Override
    public void run() {
        while(ticketNum-->0){
//            String name = Thread.currentThread().getName();
//            System.out.println(name+"抢到第"+ticketNum--+"张票");
//            new TicketServiceImpl().rob(ticketNum);
//            new TicketServiceU().rob(ticketNum);
            ticketService.rob(ticketNum);
        }
    }
}