package com.ocean.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocean.common.Constants;
import com.ocean.common.Result;
import com.ocean.controller.dto.UserDTO;
import com.ocean.entity.User;
import com.ocean.exception.ServiceException;
import com.ocean.mapper.UserMapper;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextHorzOverflowType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    private static final Log LOG = Log.get();

//    @Autowired
//    UserMapper userMapper;

    public boolean saveUser(User user) {
//        主键判断保存|更新
//        if (!userMapper.getIdList().contains(user.getId())) {
//            return save(user);
//        } else {
//            return updateById(user);
//        }
        return saveOrUpdate(user);
    }


    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);

        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
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
}
