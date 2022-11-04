package com.example.recalccustomer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.recalccustomer.pojo.Customer;
import dto.CustomerFinDto;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

//    @MapKey 是MyBatis框架的注解，作用是将List结果集转换成key-value形式的Map结果集，方便快速从结果集中查询指定结果。
    @MapKey("customerId")
    Map<Long, CustomerFinDto> findFinInfo(@Param("ids") List<Long> ids);
}
