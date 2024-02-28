package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.EQTL;
import com.example.demo.mapper.EQTLMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/eqtl")
public class EQTLController {
    @Resource
    EQTLMapper eQTLMapper;

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        //        LambdaQueryWrapper<Gwas> wrapper = Wrappers.<Gwas>lambdaQuery();
        Page<EQTL> eQTLPage = eQTLMapper.selectPage(
                new Page<>(pageNum, pageSize),
                Wrappers.<EQTL>lambdaQuery().like(EQTL::getGene, search).or()
                        .like(EQTL::getSnp, search).or().like(EQTL::getPvalue, search)
        );
        return Result.success(eQTLPage);
    }
}
