package com.ocean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("sys_goods")
public class Goods {
    @TableId("lost_id")
    private int id;
    @TableField("lost_name")
    private String name;
    @TableField("lost_description")
    private String description;
    @TableField("lost_position")
    private String position;
    @TableField("lost_date")
    private Date date;

}
