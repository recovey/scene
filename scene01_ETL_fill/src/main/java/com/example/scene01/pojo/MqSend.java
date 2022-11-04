package com.example.scene01.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("mqsend")
public class MqSend {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private int customerId;
    private String customerName;
}
