package com.example.recalcsend.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CommonUtils {
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
