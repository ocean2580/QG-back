package com.ocean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_student")
public class Student {
    @TableId(value = "student_no",type = IdType.AUTO)
    private Integer id;
    private String institute;
    private String grade;
    @TableField("class")
    private String studentClass;
    @TableField("name")
    private String studentName;
}
