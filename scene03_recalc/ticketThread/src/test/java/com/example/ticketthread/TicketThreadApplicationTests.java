package com.example.ticketthread;

import com.example.ticketthread.mapper.TicketMapper;
import com.example.ticketthread.pojo.Ticket;
import com.example.ticketthread.service.TicketService;
import com.example.ticketthread.service.impl.TicketServiceImpl;
import com.example.ticketthread.threadTest.TicketThread;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@SpringBootTest
class TicketThreadApplicationTests {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TicketService ticketService;

    @Test
    void contextLoads() {
    }

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    void TestA1() {  // 生成优惠卷
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            hashMap.put((i + 1) + "", "1");  //不能用int Integer 会报类型无法装转的错误 ，key是序号，value 1代表未抢，0代表已抢
        }
        redisTemplate.opsForHash().putAll("ticketHash", hashMap);
    }

    @Test
    void TestA11() {
        HashMap<String, Ticket> hashMap = new HashMap<>();
//        ArrayList<Ticket> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Ticket ticket = new Ticket(i + 1, 0);
            hashMap.put(i + "", ticket);
//            list.add(ticket);
        }
        redisTemplate.opsForHash().putAll("ticketHash2", hashMap);
    }

    @Test
    void TestA2() {
        HashMap<String, Ticket> hashMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            Ticket ticket = (Ticket) redisTemplate.opsForHash().get("ticketHash2", (i + 1) + "");
            hashMap.put((i + 1) + "", ticket);
        }
        System.out.println(hashMap.size());
        Integer num = (Integer) redisTemplate.opsForValue().get("num");

//        String version;

        //---------------已拿到数据-------------
//        redisTemplate.execute(new SessionCallback<List<Object>>() {
//            @Override
//            public <K, V> List<Object> execute(RedisOperations<K, V> operations) throws DataAccessException {
//
//                Object hash2 = operations.opsForHash().get("ticketHash2", 1 + "");
//                return null;
//            }
//        });
        SessionCallback<Object> sessionCallback = new SessionCallback<Object>() {  // 这里就是执行事务
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                //code here
                if (hashMap.size() == 0) return null;
                redisOperations.watch("version");
                redisOperations.multi();
//                redisOperations.opsForValue().set("num",num-1);
                redisOperations.opsForValue().decrement("num", 1); //票数 -1
                redisOperations.opsForSet().add("role", 1); // 用户+1 ，i 就是抢到的角色
                return redisOperations.exec();
            }
        };
    }

    @Test
    void TestA3() {
        ticketService.generate();
    }

    @Test
    void TestA4() {
        redisTemplate.watch("TicketNum");
        redisTemplate.multi();
        redisTemplate.opsForValue().decrement("TicketNum", 1);
        redisTemplate.exec();
    }

    @Test
    void TestA5() throws InterruptedException {
        Runnable runable = new Runnable() {
            @Override
            public void run() {
                new TicketServiceImpl().rob(1);
            }
        };
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(runable, "thread-" + (i + 1));
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    @Test
    void TestA6() {
        ticketService.rob(3);
    }

    @Test
    void TestA7() {
        //创建线程
//        TicketThread thread1 = new TicketThread();
//        TicketThread thread2 = new TicketThread();
//        TicketThread thread3 = new TicketThread();
//        //启动线程
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        static int ticketNum = 1000;
        for (int i = 1; i < 1000; i++) {
            new Thread(new Runnable() {
//                @Autowired
//                TicketService ticketService;
                @Override
                public void run() {
                    ticketService.rob(new Random().nextInt());
                }
            }).start();
        }

    }

    @Test
    void TestA8() throws InterruptedException {
        Thread.sleep(15 * 1000);
        ExecutorService service = Executors.newCachedThreadPool();
        Object[] objects = new Object[15];
        for (int i = 0; i < 15; i++) {
            final int finalI = i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(finalI * 200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    new TicketServiceImpl().rob(1);
                    System.out.println("任务：" + finalI);
                }
            });
        }
    }

    @Test
    void TestA9(){
        System.out.println(new Random().nextInt(1000));
    }
}
