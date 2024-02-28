package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("ewas")
@Data
public class Ewas {
    @TableId(type = IdType.INPUT)
    private String id;
    private String smp;
    private String phenotype;
    private String maf;
    private String pvalue;
}

