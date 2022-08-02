package com.ocean.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocean.common.Constants;
import com.ocean.common.Result;
import com.ocean.controller.dto.UserDTO;
import com.ocean.entity.Menu;
import com.ocean.entity.RoleMenu;
import com.ocean.entity.User;
import com.ocean.exception.ServiceException;
import com.ocean.mapper.RoleMapper;
import com.ocean.mapper.RoleMenuMapper;
import com.ocean.mapper.UserMapper;
import com.ocean.service.IUserService;
import com.ocean.utils.TokenUtils;
import org.apache.commons.collections.list.AbstractLinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Log LOG = Log.get();

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private MenuServiceImpl menuService;

    public boolean saveUser(User user) {
        //        主键判断保存|更新
        return saveOrUpdate(user);
    }


    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);

        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            // 设置token
            String token = TokenUtils.generateToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);


            String roleFlag = one.getRole();    // ROLE_...
            // 设置用户菜单列表
            List<Menu> roleMenus = getRoleMenus(roleFlag);

            userDTO.setMenus(roleMenus);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);

        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO,one,true);
            save(one);
        } else {
            throw new ServiceException(Constants.CODE_600,"用户已存在");
        }

        return one;
    }

    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;

        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }

    /**
     * 当前角色菜单列表
     * @param roleFlag
     * @return
     */
    private List<Menu> getRoleMenus(String roleFlag) {
        Integer roleId = roleMapper.selectByFlag(roleFlag);

        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);

        // 系统所有菜单
        List<Menu> menus = menuService.findMenus("");
        // 筛选后的list
        List<Menu> roleMenus = new ArrayList<>();
        // 筛选当前用户角色的菜单
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }
}
