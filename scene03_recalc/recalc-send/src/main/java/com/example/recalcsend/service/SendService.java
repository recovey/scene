package com.example.recalcsend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.recalcsend.pojo.Send;

import java.util.List;

public interface SendService extends IService<Send> {

    Boolean computerVolume();

    Boolean computerVolumePage();

    Boolean computerVolumePage2();

    List<Send> getByScanTime(String start, String end);

}
