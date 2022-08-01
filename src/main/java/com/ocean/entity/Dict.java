package com.ocean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@TableName("sys_dict")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dict {

    private String name;
    private String type;
    private String value;
}
