package com.example.ticketthread.threadTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

public class TicketServiceU {

    //@Autowired
    //RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();

    public static void rob(int id) {

        String key = "TicketNum";
        new RedisTemplate<>().execute(new SessionCallback<Object>() {
            @Override
            @SuppressWarnings({"unchecked"})
            public Object execute(RedisOperations operations) throws DataAccessException {

                boolean flag = (int) operations.opsForValue().get(key) == 0 ||
                        operations.opsForSet().isMember("users", "用户" + id);
                System.out.println(id + ">" + flag);
                if (flag) return null;

                operations.watch(key);  // watch某个key,当该key被其它客户端改变时,则会中断当前的操作

                operations.multi(); //开始事务
                operations.opsForValue().decrement("TicketNum", 1); // 票数-1
                operations.opsForSet().add("users", "用户" + id);  // 用户+1 ，i 就是抢到的角色
                try {
                    operations.exec(); //提交事务
                    // 如果秒杀数量没有了，则秒活动结束，更新库中的秒杀信息
                } catch (Exception e) { //如果key被改变,提交事务时这里会报异常
                }
                return null;
            }

        });
    }
}
