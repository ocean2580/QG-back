package com.ocean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_file")
public class Files {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String type;
    private long size;
    private String url;
    @TableField("is_delete")
    private Boolean isDelete;
    private Boolean enable;
    private String md5;
}
