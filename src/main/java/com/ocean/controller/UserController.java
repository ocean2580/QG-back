package com.ocean.controller;

import com.ocean.entity.User;
import com.ocean.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public List<User> run() {
        return userMapper.findAll();
    }
}
