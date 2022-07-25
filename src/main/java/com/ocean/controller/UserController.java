package com.ocean.controller;

import com.ocean.entity.User;
import com.ocean.mapper.UserMapper;
import com.ocean.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public Integer save(@RequestBody User user) {   // 前端传来的json数据映射成对象
        // 新增|更新
        return userService.save(user);
    }

    @GetMapping("/show")
    public List<User> show() {
        return userMapper.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable Integer id) {   // restful
        return userMapper.deleteById(id);
    }
}
