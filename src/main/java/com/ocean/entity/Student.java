package com.ocean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sys_student")
public class Student {
    @TableId("student_no")
    private int id;
    private String institute;
    private int grade;
    @TableField("class")
    private String sClass;
    private String name;
}
