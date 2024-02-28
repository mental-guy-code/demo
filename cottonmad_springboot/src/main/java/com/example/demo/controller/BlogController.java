package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Blog;
import com.example.demo.mapper.BlogMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Resource
    BlogMapper blogMapper;

    // post插入 put更新 get查询 delete删除
    @PostMapping
    public Result<?> save(@RequestBody Blog blog){
        blog.setTime(new Date());
        if (blog.getContent() ==null){
            return Result.failed();
        }
        blogMapper.insert(blog);
        return Result.success(blog);
    }

    @PutMapping //函数名任意
    public Result<?> update(@RequestBody Blog blog){
        blog.setTime(new Date());
        blogMapper.updateById(blog);
        return Result.success("success");
    }

    // @DeleteMapping("/{id}/{aa}") 取参数
    // public Result<?> delete(@PathVariable Long id, @PathVariable String aa){
    @DeleteMapping
    public Result<?> delete(@RequestParam Integer id){
        blogMapper.deleteById(id);
        return Result.success("success");
    }

//    @PostMapping("/login")
//    public Result<?> login(@RequestBody Blog blog){
//        LambdaQueryWrapper<Blog> blogLambdaQueryWrapper = Wrappers.<Blog>lambdaQuery();
//        Blog res = blogMapper.selectOne(blogLambdaQueryWrapper.eq(Blog::getId, blog.getId())
//                .eq(Blog::getPassword, blog.getPassword()));
//        if (res == null){
//            return Result.failed();
//        }
//        return Result.success(res);
//    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        //        LambdaQueryWrapper<Gwas> wrapper = Wrappers.<Gwas>lambdaQuery();
        LambdaQueryWrapper<Blog> blogLambdaQueryWrapper = Wrappers.<Blog>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            blogLambdaQueryWrapper.like(Blog::getContent, search).or().like(Blog::getTitle, search);
        }
        Page<Blog> blogPage = blogMapper.selectPage(
                new Page<>(pageNum, pageSize), blogLambdaQueryWrapper.orderByDesc(Blog::getTime)
        );
        return Result.success(blogPage);
    }
}
