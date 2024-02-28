package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Ewas;
import com.example.demo.mapper.EwasMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ewas")
public class EwasController {
    @Resource
    EwasMapper ewasMapper;

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        //        LambdaQueryWrapper<Gwas> wrapper = Wrappers.<Gwas>lambdaQuery();
        Page<Ewas> ewasPage = ewasMapper.selectPage(
                new Page<>(pageNum, pageSize),
                Wrappers.<Ewas>lambdaQuery().like(Ewas::getSmp, search).or()
                        .like(Ewas::getPhenotype, search)
        );
        return Result.success(ewasPage);
    }
}
