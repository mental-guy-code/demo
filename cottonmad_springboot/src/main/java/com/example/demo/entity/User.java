package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.yaml.snakeyaml.events.Event;

@TableName("Users")
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private Integer age;
    private String sex;
    private String address;
    private String cover;
}
