package com.example.recalcsend.service.impl;

import Vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.recalcsend.api.CustomerConfigService;
import com.example.recalcsend.mapper.SendMapper;
import com.example.recalcsend.pojo.Send;
import com.example.recalcsend.service.SendService;
import dto.CustomerFinDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SendServiceImpl extends ServiceImpl<SendMapper, Send> implements SendService {


    @Autowired
    CustomerConfigService customerConfigService;

    @Override
    public Boolean computerVolume() {
        long startTime = System.currentTimeMillis(); //获取开始时间

        List<Send> sendList = getByScanTime("2021-10-03 00:00:00", "2021-10-03 00:00:00");
        sendList.forEach(send -> {
            double xishu = customerConfigService.getxishu(send.getFinObjId());
            double sum = send.getHeight() * send.getWidth() * send.getLength() * xishu;
            BigDecimal two = new BigDecimal(sum);
            double v = two.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            send.setVolumeWeight(v);
        });
        boolean batch = super.updateBatchById(sendList);
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) / 1000 + "s"); //第一次->程序运行时间：850s
        return batch;
    }


    @Override
    public Boolean computerVolumePage() {
        long startTime = System.currentTimeMillis(); //获取开始时间
        int current = 1;
        int size = 50_000;
        // 以扫描时间作为查询条件
        LambdaQueryWrapper<Send> query = new LambdaQueryWrapper<>();
        query.between(Send::getScanTime, "2021-10-01 00:00:00", "2021-10-02 00:00:00");
        long count = count(query);
        for (int i = 0; i < count / size + 1; i++) handlePage(current, size, query, i);

        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) / 1000 + "s"); //第一次->程序运行时间：850s
        return true;
    }


    @Override
    public Boolean computerVolumePage2() {
        long startTime = System.currentTimeMillis(); //获取开始时间

        int current = 1;
        int size = 50_000;
        LambdaQueryWrapper<Send> query = new LambdaQueryWrapper<>();
        query.between(Send::getScanTime, "2021-10-03 00:00:00", "2021-10-30 00:00:00");
        long count = count(query);
        for (int i = 0; i < count / size + 1; i++) handlePage2(current, size, query, i);

        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) / 1000 + "s"); //第一次->程序运行时间：850s
        return true;
    }


    @Autowired
    private RedisTemplate redisTemplate;

    private void handlePage2(int current, int size, LambdaQueryWrapper<Send> query, int i) {
        Page<Send> of = Page.of(current + i, size);
        Page<Send> page = page(of, query);
        List<Send> records = page.getRecords();

        // 提取当前寄件集合中的结算id
//        List<Long> finObjIds = records.stream().map(Send::getFinObjId).collect(Collectors.toList());
//        Result<Map<Long, CustomerFinDto>> result = customerConfigService.findFinInfo(finObjIds);
        records.forEach(send -> {
//            double weightRate = result.getData().get(send.getFinObjId()).getWeightRate();
            //System.out.println(redisTemplate.opsForHash().get("XiShu",send.getFinObjId()+""));
            double weightRate = (double) redisTemplate.opsForHash().get("XiShu", send.getFinObjId() + "");
            double sum = send.getHeight() * send.getWidth() * send.getLength() * weightRate;
            BigDecimal two = new BigDecimal(sum);
            double v = two.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            send.setVolumeWeight(v);
        });
        updateBatchById(records, 50000); //不改批次数量 程序运行时间：543s  // 走redis用时734s 第二次 572s
        // 改到50000 使用redis 用时524s
    }

    private void handlePage(int current, int size, LambdaQueryWrapper<Send> query, int i) {
        Page<Send> of = Page.of(current + i, size);
        Page<Send> page = page(of, query);
        List<Send> records = page.getRecords();
        records.forEach(send -> {
            double xishu = customerConfigService.getxishu(send.getFinObjId());
            double sum = send.getHeight() * send.getWidth() * send.getLength() * xishu;
            BigDecimal two = new BigDecimal(sum);
            double v = two.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            send.setVolumeWeight(v);
        });
        updateBatchById(records, 50000);
    }

    @Override
    public List<Send> getByScanTime(String start, String end) {
        LambdaQueryWrapper<Send> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.between(Send::getScanTime, start, end);
        return super.list(queryWrapper);
    }
}
