package com.example.demo.service;

import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService{
    @Resource
    private UserMapper userMapper;

    public User save(User user) {
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return user;
    }

    public String update(User user) {
        userMapper.updateById(user);
        return "success";
    }

    public String delete(Integer id) {
        userMapper.deleteById(id);
        return "success";
    }

//    public User login(User user) {
//        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery();
//        queryWrapper.eq(User::getId, user.getId()).eq(User::getPassword, user.getPassword());
//        return userMapper.selectOne(queryWrapper);
//    }

    public Page<User> findPage(Integer pageNum, Integer pageSize, String search) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery();

        if (StrUtil.isNotBlank(search)) {
            queryWrapper.like(User::getNickname, search).or().like(User::getUsername, search);
        }

        return userMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery();
//        queryWrapper.eq(User::getId, Integer.parseInt(username));
//        User user = userMapper.selectOne(queryWrapper);

        // 使用MyBatis Plus根据用户名查询用户
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
//        System.out.println(username+"mimamimamima");

        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误: "+ username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}
