package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.MeQTL;
import com.example.demo.mapper.MeQTLMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/meqtl")
public class MeQTLController {
    @Resource
    MeQTLMapper meQTLMapper;

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        //        LambdaQueryWrapper<Gwas> wrapper = Wrappers.<Gwas>lambdaQuery();
        Page<MeQTL> meQTLPage = meQTLMapper.selectPage(
                new Page<>(pageNum, pageSize),
                Wrappers.<MeQTL>lambdaQuery().like(MeQTL::getSmp, search).or()
                        .like(MeQTL::getSnp, search)
        );
        return Result.success(meQTLPage);
    }
}
