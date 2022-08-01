package com.ocean.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ocean.entity.Role;

import java.util.List;

public interface IRoleService extends IService<Role> {

    void setRoleMenu(Integer roleId, List<Integer> menuIds);

    List<Integer> getRoleMenu(Integer roleId);
}
