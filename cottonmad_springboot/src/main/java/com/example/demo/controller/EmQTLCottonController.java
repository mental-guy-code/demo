package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.EmQTLCotton;
import com.example.demo.mapper.EmQTLCottonMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/emqtlcotton")
public class EmQTLCottonController {
    @Resource
    EmQTLCottonMapper emQTLCottonMapper;

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        //        LambdaQueryWrapper<Gwas> wrapper = Wrappers.<Gwas>lambdaQuery();
        Page<EmQTLCotton> emQTLCottonPage = emQTLCottonMapper.selectPage(
                new Page<>(pageNum, pageSize),
                Wrappers.<EmQTLCotton>lambdaQuery().like(EmQTLCotton::getGene, search).or()
                        .like(EmQTLCotton::getSmp, search)
        );
        return Result.success(emQTLCottonPage);
    }
}
