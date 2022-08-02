package com.ocean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
@ToString
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    @JsonIgnore // 不展示
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String address;
    @TableField("avatar_url")   // 自动将 下划线 转为 驼峰
    private String avatarUrl;
    @TableField("create_time")
    private Date createTime;

    private String role;

}
