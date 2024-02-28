package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.EmQTLAT;
import com.example.demo.mapper.EmQTLATMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/emqtlat")
public class EmQTLATController {
    @Resource
    EmQTLATMapper emQTLATMapper;

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        //        LambdaQueryWrapper<Gwas> wrapper = Wrappers.<Gwas>lambdaQuery();
        Page<EmQTLAT> emQTLATPage = emQTLATMapper.selectPage(
                new Page<>(pageNum, pageSize),
                Wrappers.<EmQTLAT>lambdaQuery().like(EmQTLAT::getGene, search).or()
                        .like(EmQTLAT::getSmp, search)
        );
        return Result.success(emQTLATPage);
    }
}
