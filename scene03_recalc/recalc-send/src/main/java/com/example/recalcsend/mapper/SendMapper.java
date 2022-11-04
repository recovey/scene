package com.example.recalcsend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.recalcsend.pojo.Send;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SendMapper extends BaseMapper<Send> {

//    List<Send> pageQuery(@Param("start") int start, @Param("end") int end);

}
