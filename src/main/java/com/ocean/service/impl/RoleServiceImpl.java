package com.ocean.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocean.entity.Menu;
import com.ocean.entity.Role;
import com.ocean.entity.RoleMenu;
import com.ocean.mapper.RoleMapper;
import com.ocean.mapper.RoleMenuMapper;
import com.ocean.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private MenuServiceImpl menuService;

    @Transactional
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {

        // 先删除
        roleMenuMapper.deleteByRoleId(roleId);
        // 再绑定
        List<Integer> menuIdsCopy = CollUtil.newArrayList(menuIds);
        for (Integer menuId : menuIds) {
            Menu menu = menuService.getById(menuId);
            if (menu.getPid() != null && !menuIdsCopy.contains(menu.getPid())) {    // 二级菜单的父级处理
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menu.getPid());
                roleMenuMapper.insert(roleMenu);

                menuIdsCopy.add(menu.getPid());
            }

            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
    }

    @Override
    public List<Integer> getRoleMenu(Integer roleId) {
        return roleMenuMapper.selectByRoleId(roleId);
    }
}
