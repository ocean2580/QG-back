package com.ocean.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ocean.controller.dto.UserDTO;
import com.ocean.entity.User;

public interface IUserService extends IService<User> {
    boolean saveUser(User user);
    UserDTO login(UserDTO userDTO);
    User register(UserDTO userDTO);


}
