package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping
    public Result<?> save(@RequestBody User user){
        User savedUser = userService.save(user);
        return Result.success(savedUser);
    }

    @PutMapping
    public Result<?> update(@RequestBody User user){
        String result = userService.update(user);
        return Result.success(result);
    }

    @DeleteMapping
    public Result<?> delete(@RequestParam Integer id){
        String result = userService.delete(id);
        return Result.success(result);
    }


//    @PostMapping("/login")
//    public Result<?> login(@RequestBody User user){
//        User res = userService.login(user);
//
//        if (res == null){
//            return Result.failed();
//        }
//
//        return Result.success(res);
//    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        Page<User> usersPage = userService.findPage(pageNum, pageSize, search);
        return Result.success(usersPage);
    }
}
