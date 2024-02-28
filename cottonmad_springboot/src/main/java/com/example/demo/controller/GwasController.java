package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Gwas;
import com.example.demo.mapper.GwasMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/gwas")
public class GwasController {

    @Resource
    GwasMapper gwasMapper;

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        //        LambdaQueryWrapper<Gwas> wrapper = Wrappers.<Gwas>lambdaQuery();
        Page<Gwas> gwasPage = gwasMapper.selectPage(
                new Page<>(pageNum, pageSize),
                Wrappers.<Gwas>lambdaQuery().like(Gwas::getSnp, search).or()
                        .like(Gwas::getGene, search)
        );
        return Result.success(gwasPage);
    }

}
