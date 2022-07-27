package com.ocean.controller;

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
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @PostMapping
    public Integer save(@RequestBody User user) {   // 前端传来的json数据映射成对象
        // 新增|更新
        return userService.save(user);
    }

    @GetMapping
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable Integer id) {   // restful
        return userMapper.deleteById(id);
    }


    /**
     *  // @RequestParam 接受 ?pageNum=1&pageSize=10
     * @param num = (pageNum-1) * size
     * @param size = pageSize
     * @return 分页查询结果
     */
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam("pageNum") Integer num,
                                        @RequestParam("pageSize") Integer size,
                                        @RequestParam("username") String name) {
        num = (num-1) * size;
        List<User> data = userMapper.selectPage(num, size, name);
        Integer total = userMapper.selectTotal(name);
        Map<String, Object> map = new HashMap<>();
        map.put("data",data);
        map.put("total",total);
        return map;
    }
}
