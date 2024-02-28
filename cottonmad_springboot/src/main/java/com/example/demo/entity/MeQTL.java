package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("meqtl")
@Data
public class MeQTL {
    @TableId(type = IdType.INPUT)
    private String id;
    private String smp;
    private String num;
    private String snp;
    private String distance;
    private String pvalue;
    private String slope;
    private String meType;
}
