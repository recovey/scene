package com.example.recalcsend.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_send")
public class Send {

    @TableId(type = IdType.AUTO)
    private Long sendId;

    private double length;
    private double width;
    private double height;
    private double volumeWeight;
    private Long finObjId;
    private Date scanTime;
//    @TableId
//    private Long sendId;
//    private Long finObjId;
//    private BigDecimal length;
//    private BigDecimal width;
//    private BigDecimal height;
//    private BigDecimal volumeWeight;
//    private Date scanTime;
}
