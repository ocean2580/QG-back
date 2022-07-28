package com.ocean.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.entity.User;
import com.ocean.mapper.UserMapper;
import com.ocean.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public boolean save(@RequestBody User user) {   // 前端传来的json数据映射成对象
        // 新增|更新
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> findAll() {
        return userService.list();
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {   // restful
        return userService.removeById(id);
    }


    /**
     *  // @RequestParam 接受 ?pageNum=1&pageSize=10
     * @param num = (pageNum-1) * size
     * @param size = pageSize
     * @return 分页查询结果
     */
//    @GetMapping("/page")
//    public Map<String, Object> findPage(@RequestParam("pageNum") Integer num,
//                                        @RequestParam("pageSize") Integer size,
//                                        @RequestParam("username") String name) {
//        num = (num-1) * size;
//        List<User> data = userMapper.selectPage(num, size, name);
//        Integer total = userMapper.selectTotal(name);
//        Map<String, Object> map = new HashMap<>();
//        map.put("data",data);
//        map.put("total",total);
//        return map;
//    }

//    mybatis-plus 写法
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam("pageNum") Integer num,
                                        @RequestParam("pageSize") Integer size,
                                        @RequestParam(value = "username",defaultValue = "") String name,
                                @RequestParam(value = "email",defaultValue = "") String email,
                                @RequestParam(value = "address",defaultValue = "")String address) {
        IPage<User> page = new Page<>(num,size);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // and 连接
        if (!"".equals(name)) {
            queryWrapper.like("username",name);
        }
        if (!"".equals(email)) {
            queryWrapper.like("email",email);
        }
        if (!"".equals(address)) {
            queryWrapper.like("address",address);
        }
//        queryWrapper.orderByDesc("id");
        return userService.page(page, queryWrapper);
    }
}
