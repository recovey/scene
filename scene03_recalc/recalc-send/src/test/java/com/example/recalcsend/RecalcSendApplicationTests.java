package com.example.recalcsend;

import com.example.recalcsend.pojo.Send;
import com.example.recalcsend.service.SendService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootTest
class RecalcSendApplicationTests {

    @Autowired
    private SendService sendService;

//    @Test
//    void saveSend() throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        List<Date> dates = randomDate("2021-03-01", "2021-04-01", 1000000);
//        List<Send> sendList = new ArrayList<>();
//
//        Random random = new Random();
//        long startTime = System.currentTimeMillis(); //获取开始时间
//
//        for (int i = 1; i < 100002; i++) {
//            Send send = new Send();
//            send.setHeight(randomDouble());
//            send.setLength(randomDouble());
//            send.setWidth(randomDouble());
//            String format = sdf.format(dates.get(i-1));
//            send.setScan_time(sdf.parse(format));
//            send.setFin_obj_id((long) random.nextInt(90000));
//            sendList.add(send);
//        }
//        sendService.saveBatch(sendList);
//
//        long endTime = System.currentTimeMillis(); //获取结束时间
//        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
////        dates.forEach(t -> System.out.println(sdf.format(t)));
//    }
//
//    @Test
//    void saveSend2() throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        List<Date> dates = randomDate("2021-02-01", "2021-03-01", 1000000);
//        List<Send> sendList = new ArrayList<>();
//
//        Random random = new Random();
//        long startTime = System.currentTimeMillis(); //获取开始时间
//
//        for (int i = 1; i < 100002; i++) {
//            Send send = new Send();
//            send.setHeight(randomDouble());
//            send.setLength(randomDouble());
//            send.setWidth(randomDouble());
//            String format = sdf.format(dates.get(i-1));
//            send.setScan_time(sdf.parse(format));
//            send.setFin_obj_id((long) random.nextInt(90000));
//            sendList.add(send);
//        }
//        sendService.saveBatch(sendList);
//
//        long endTime = System.currentTimeMillis(); //获取结束时间
//        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
////        dates.forEach(t -> System.out.println(sdf.format(t)));
//    }
//
//    @Test
//    void saveSend3() throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        List<Date> dates = randomDate("2021-01-01", "2021-02-01", 1000000);
//        List<Send> sendList = new ArrayList<>();
//
//        Random random = new Random();
//        long startTime = System.currentTimeMillis(); //获取开始时间
//
//        for (int i = 1; i < 100002; i++) {
//            Send send = new Send();
//            send.setHeight(randomDouble());
//            send.setLength(randomDouble());
//            send.setWidth(randomDouble());
//            String format = sdf.format(dates.get(i-1));
//            send.setScan_time(sdf.parse(format));
//            send.setFin_obj_id((long) random.nextInt(90000));
//            sendList.add(send);
//        }
//        sendService.saveBatch(sendList);
//
//        long endTime = System.currentTimeMillis(); //获取结束时间
//        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
////        dates.forEach(t -> System.out.println(sdf.format(t)));
//    }
//
//    @Test
//    void saveSend4() throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        List<Date> dates = randomDate("2021-04-01", "2021-05-01", 1000000);
//        List<Send> sendList = new ArrayList<>();
//
//        Random random = new Random();
//        long startTime = System.currentTimeMillis(); //获取开始时间
//
//        for (int i = 1; i < 100002; i++) {
//            Send send = new Send();
//            send.setHeight(randomDouble());
//            send.setLength(randomDouble());
//            send.setWidth(randomDouble());
//            String format = sdf.format(dates.get(i-1));
//            send.setScan_time(sdf.parse(format));
//            send.setFin_obj_id((long) random.nextInt(90000));
//            sendList.add(send);
//        }
//        sendService.saveBatch(sendList);
//
//        long endTime = System.currentTimeMillis(); //获取结束时间
//        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
////        dates.forEach(t -> System.out.println(sdf.format(t)));
//    }
//
//    @Test
//    void saveSend5() throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        List<Date> dates = randomDate("2021-05-01", "2021-06-01", 1000000);
//        List<Send> sendList = new ArrayList<>();
//
//        Random random = new Random();
//        long startTime = System.currentTimeMillis(); //获取开始时间
//
//        for (int i = 1; i < 100002; i++) {
//            Send send = new Send();
//            send.setHeight(randomDouble());
//            send.setLength(randomDouble());
//            send.setWidth(randomDouble());
//            String format = sdf.format(dates.get(i-1));
//            send.setScan_time(sdf.parse(format));
//            send.setFin_obj_id((long) random.nextInt(90000));
//            sendList.add(send);
//        }
//        sendService.saveBatch(sendList);
//
//        long endTime = System.currentTimeMillis(); //获取结束时间
//        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
////        dates.forEach(t -> System.out.println(sdf.format(t)));
//    }
//
//    @Test
//    void saveSend6() throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        List<Date> dates = randomDate("2021-06-01", "2021-07-01", 1000000);
//        List<Send> sendList = new ArrayList<>();
//
//        Random random = new Random();
//        long startTime = System.currentTimeMillis(); //获取开始时间
//
//        for (int i = 1; i < 90001; i++) {
//            Send send = new Send();
//            send.setHeight(randomDouble());
//            send.setLength(randomDouble());
//            send.setWidth(randomDouble());
//            String format = sdf.format(dates.get(i-1));
//            send.setScan_time(sdf.parse(format));
//            send.setFin_obj_id((long) random.nextInt(90000));
//            sendList.add(send);
//        }
//        sendService.saveBatch(sendList);
//
//        long endTime = System.currentTimeMillis(); //获取结束时间
//        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
////        dates.forEach(t -> System.out.println(sdf.format(t)));
//    }
//
//    @Test
//    void saveSend7() throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        List<Date> dates = randomDate("2021-07-01", "2021-08-01", 1000000);
//        List<Send> sendList = new ArrayList<>();
//
//        Random random = new Random();
//        long startTime = System.currentTimeMillis(); //获取开始时间
//
//        for (int i = 1; i < 100002; i++) {
//            Send send = new Send();
//            send.setHeight(randomDouble());
//            send.setLength(randomDouble());
//            send.setWidth(randomDouble());
//            String format = sdf.format(dates.get(i-1));
//            send.setScan_time(sdf.parse(format));
//            send.setFin_obj_id((long) random.nextInt(90000));
//            sendList.add(send);
//        }
//        sendService.saveBatch(sendList);
//
//        long endTime = System.currentTimeMillis(); //获取结束时间
//        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
////        dates.forEach(t -> System.out.println(sdf.format(t)));
//    }
//
//    @Test
//    void saveSend8() throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        List<Date> dates = randomDate("2021-08-01", "2021-09-01", 1000000);
//        List<Send> sendList = new ArrayList<>();
//
//        Random random = new Random();
//        long startTime = System.currentTimeMillis(); //获取开始时间
//
//        for (int i = 1; i < 100002; i++) {
//            Send send = new Send();
//            send.setHeight(randomDouble());
//            send.setLength(randomDouble());
//            send.setWidth(randomDouble());
//            String format = sdf.format(dates.get(i-1));
//            send.setScan_time(sdf.parse(format));
//            send.setFin_obj_id((long) random.nextInt(90000));
//            sendList.add(send);
//        }
//        sendService.saveBatch(sendList);
//
//        long endTime = System.currentTimeMillis(); //获取结束时间
//        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
////        dates.forEach(t -> System.out.println(sdf.format(t)));
//    }
//
//    @Test
//    void saveSend9() throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        List<Date> dates = randomDate("2021-09-01", "2021-10-01", 1000000);
//        List<Send> sendList = new ArrayList<>();
//
//        Random random = new Random();
//        long startTime = System.currentTimeMillis(); //获取开始时间
//
//        for (int i = 1; i < 100002; i++) {
//            Send send = new Send();
//            send.setHeight(randomDouble());
//            send.setLength(randomDouble());
//            send.setWidth(randomDouble());
//            String format = sdf.format(dates.get(i-1));
//            send.setScan_time(sdf.parse(format));
//            send.setFin_obj_id((long) random.nextInt(90000));
//            sendList.add(send);
//        }
//        sendService.saveBatch(sendList);
//
//        long endTime = System.currentTimeMillis(); //获取结束时间
//        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
////        dates.forEach(t -> System.out.println(sdf.format(t)));
//    }
//
//    @Test
//    void saveSend10() throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        List<Date> dates = randomDate("2021-10-01", "2021-11-01", 300_000);
//        List<Send> sendList = new ArrayList<>();
//
//        Random random = new Random();
//        long startTime = System.currentTimeMillis(); //获取开始时间
//
//        for (int i = 1; i < 300_001; i++) {
//            Send send = new Send();
//            send.setHeight(randomDouble());
//            send.setLength(randomDouble());
//            send.setWidth(randomDouble());
//            String format = sdf.format(dates.get(i-1));
//            send.setScan_time(sdf.parse(format));
//            send.setFin_obj_id((long) random.nextInt(10_000)+90_000);
//            sendList.add(send);
//        }
//        sendService.saveBatch(sendList);
//
//        long endTime = System.currentTimeMillis(); //获取结束时间
//        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
////        dates.forEach(t -> System.out.println(sdf.format(t)));
//    }

    public static List<Date> randomDate(String start, String end, int size) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = sdf.parse(start);
        Date endTime = sdf.parse(end);

        Random random = new Random();
        List<Date> dates = random.longs(size, startTime.getTime(), endTime.getTime()).mapToObj(t -> new Date(t)).collect(Collectors.toList());

//        dates.sort((t1,t2)->{ // 排序
//            return t2.compareTo(t1);
//        });

        return dates;
    }
    public static Double randomDouble(){
        Random random = new Random();
        double num = random.nextDouble() * 10;
        DecimalFormat df = new DecimalFormat("0.00");
        String format = df.format(num);
        return  Double.parseDouble(format);
    }
}
