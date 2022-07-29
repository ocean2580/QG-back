package com.ocean.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocean.controller.dto.UserDTO;
import com.ocean.entity.User;
import com.ocean.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

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


    public boolean login(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        queryWrapper.eq("password",userDTO.getPassword());
        User one = getOne(queryWrapper);
        return one != null;
    }
}
