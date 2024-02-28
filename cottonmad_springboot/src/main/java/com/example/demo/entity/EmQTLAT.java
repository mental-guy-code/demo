package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("emqtlat")
@Data
public class EmQTLAT {
    @TableId(type = IdType.INPUT)
    private String id;
    private String gene;
    private String num;
    private String smp;
    private String distance;
    private String pvalue;
    private String slope;
    private String meType;
}
