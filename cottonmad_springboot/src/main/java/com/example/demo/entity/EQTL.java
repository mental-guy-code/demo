package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("eqtl")
@Data
public class EQTL {
    @TableId(type = IdType.INPUT)
    private String id;
    private String gene;
    private String snp;
    private String pvalue;
}
