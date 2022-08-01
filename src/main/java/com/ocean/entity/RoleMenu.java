package com.ocean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@TableName("sys_role_menu")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleMenu {

    @TableField("role_id")
    private Integer roleId;
    @TableField("menu_id")
    private Integer menuId;
}
