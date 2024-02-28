package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("gwas")
@Data
public class Gwas {
    @TableId(type = IdType.INPUT)
    private String id;
    private String snp;
    private String pvalue;
    private String maf;
    private String phenotype;
    private String reference;
    private String alteration;
    private String gene;
    private String region;
    private String arabId;
    private String arabDesc;
    private String fpkm;

}
