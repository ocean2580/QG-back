package com.ocean.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.common.Result;
import com.ocean.entity.Role;
import com.ocean.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping
    public Result save(@RequestBody Role role) {   // 前端传来的json数据映射成对象
        // 新增|更新
        return Result.success(roleService.saveOrUpdate(role));
    }

    @GetMapping
    public Result findAll() {
        return Result.success(roleService.list());
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {   // restful
        return Result.success(roleService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {   // restful
        return Result.success(roleService.removeByIds(ids));
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {

        return Result.success(roleService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam("pageNum") Integer num,
                           @RequestParam("pageSize") Integer size) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(roleService.page(new Page<>(size,num),queryWrapper));
    }

}
