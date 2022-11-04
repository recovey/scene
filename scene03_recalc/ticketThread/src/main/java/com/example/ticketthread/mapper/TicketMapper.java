package com.example.ticketthread.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ticketthread.pojo.Ticket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketMapper extends BaseMapper<Ticket> {
}
