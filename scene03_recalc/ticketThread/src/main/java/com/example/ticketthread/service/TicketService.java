package com.example.ticketthread.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ticketthread.pojo.Ticket;

public interface TicketService extends IService<Ticket> {

    void generate();

    void rob(int userId);

    void start();

}
