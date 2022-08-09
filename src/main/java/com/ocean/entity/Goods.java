package com.ocean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_goods")
public class Goods {
    @TableId(value = "lost_id", type = IdType.AUTO)
    private int id;
    @TableField("lost_name")
    private String goodsName;
    @TableField("lost_description")
    private String description;
    @TableField("lost_position")
    private String lostPosition;
    @TableField("claim_position")
    private String claimPosition;
    @TableField("lost_date")
    private Date lostDate;

}
