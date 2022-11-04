package com.example.ticketthread.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ticketthread.mapper.TicketMapper;
import com.example.ticketthread.pojo.Ticket;
import com.example.ticketthread.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public void generate() {
        HashMap<String, Ticket> hashMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            Ticket ticket = new Ticket(i + 1, 0);
            hashMap.put(i + "", ticket);
        }
        redisTemplate.opsForHash().putAll("TicketAll", hashMap);
        redisTemplate.opsForValue().set("TicketNum", 100);
    }

    @Override
    public void rob(int userId) {
        System.out.println("来了");
//HashMap<String, Ticket> hashMap = new HashMap<>();
//        for (int i = 0; i < 100; i++) {
//            Ticket ticket = (Ticket) redisTemplate.opsForHash().get("ticketHash2", (i + 1) + "");
//            hashMap.put((i + 1) + "", ticket);
//        }
//        System.out.println(hashMap.size());
//        Integer num = (Integer) redisTemplate.opsForValue().get("num");
//        redisOperations.opsForValue().set("num",num-1);


//        System.out.println(id);
//        // 如果没用票或者用户已经抢过了，那么直接返回
//        if ((int)redisTemplate.opsForValue().get("TicketNum") == 0 || redisTemplate.opsForSet().isMember("已抢用户","用户"+id)) return;
//
//        redisTemplate.watch("TicketNum");  // 监听TicketNum，一旦出现异常就执行失败
//        redisTemplate.multi(); // 执行事务
//        redisTemplate.opsForValue().decrement("TicketNum", 1); // 票数-1
//        redisTemplate.opsForSet().add("已抢用户", "用户"+id);  // 用户+1 ，i 就是抢到的角色
//        redisTemplate.exec(); // 执行代码


//        SessionCallback<Object> sessionCallback = new SessionCallback<Object>() {  // 这里就是执行事务
//            @Override
//            public Object execute(RedisOperations redisOperations) throws DataAccessException {
//                // 如果没用票或者用户已经抢过了，那么直接返回
//                if ((int)redisOperations.opsForValue().get("TicketNum") == 0 || redisOperations.opsForSet().isMember("已抢用户","用户"+id)) return null;
//
//                redisOperations.watch("TicketNum");  // 监听TicketNum，一旦出现异常就执行失败
//                redisOperations.multi(); // 执行事务
//                redisOperations.opsForValue().decrement("TicketNum", 1); // 票数-1
//                redisOperations.opsForSet().add("已抢用户", "用户"+id);  // 用户+1 ，i 就是抢到的角色
//                return redisOperations.exec(); // 执行代码
//            }
//        };
//        try {
//            Thread.sleep(6000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        String key = "TicketNum";
        redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            @SuppressWarnings({"unchecked"})
            public Object execute(RedisOperations operations) throws DataAccessException {

                boolean flag = (int) operations.opsForValue().get(key) == 0 ||
                        operations.opsForSet().isMember("users", "用户" + userId);
                System.out.println(userId + ">" + flag);
                if (flag) return null;

                operations.watch(key);  // watch某个key,当该key被其它客户端改变时,则会中断当前的操作

                operations.multi(); //开始事务
                operations.opsForValue().decrement("TicketNum", 1); // 票数-1
                operations.opsForSet().add("users", "用户" + userId);  // 用户+1 ，i 就是抢到的角色
                try {
                    operations.exec(); //提交事务
                    // 如果秒杀数量没有了，则秒活动结束，更新库中的秒杀信息
                } catch (Exception e) { //如果key被改变,提交事务时这里会报异常
                }
                return null;
            }

        });
    }

    @Override
    public void start() {
        System.out.println("来了");
    }
}
