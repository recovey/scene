package com.example.recalcsend.utils;

import com.example.recalcsend.pojo.Send;
import com.example.recalcsend.service.SendService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.example.recalcsend.utils.CommonUtils.randomDate;
import static com.example.recalcsend.utils.CommonUtils.randomDouble;

public class saveThread   extends Thread {
//
//    @Autowired
//    private SendService sendService;
//
//    static int i = 1;
//
////    @SneakyThrows
//    @Override
//    public void run() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        List<Date> dates = null;
//        try {
//            dates = randomDate("2021-03-01", "2021-04-01", 100000);
//            List<Send> sendList = new ArrayList<>();
//
//            Random random = new Random();
//            long startTime = System.currentTimeMillis(); //获取开始时间
//
//            for (;i < 100000; i++) {
//                Send send = new Send();
//                send.setHeight(randomDouble());
//                send.setLength(randomDouble());
//                send.setWidth(randomDouble());
//                String format = sdf.format(dates.get(i-1));
//                send.setScanTime(sdf.parse(format));
//                send.setFinObjId((long) random.nextInt(90000));
//                sendList.add(send);
//            }
//            sendService.saveBatch(sendList);
//
//            long endTime = System.currentTimeMillis(); //获取结束时间
//            System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
////        dates.forEach(t -> System.out.println(sdf.format(t)));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
