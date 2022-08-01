package com.ocean.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.common.Result;
import com.ocean.entity.Menu;
import com.ocean.entity.Role;
import com.ocean.service.IMenuService;
import com.ocean.service.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ocean
 * @since 2022-07-31
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuServiceImpl menuService;

    @PostMapping
    public Result save(@RequestBody Menu menu) {   // 前端传来的json数据映射成对象
        // 新增|更新
        return Result.success(menuService.saveOrUpdate(menu));
    }

    @GetMapping
    public Result findAll(@RequestParam(defaultValue = "") String name) {

        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
//        queryWrapper.orderByDesc("id");

        List<Menu> list = menuService.list();
        //  一级菜单
        List<Menu> parentNode = list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        for (Menu menu : parentNode) {
            // 一级菜单的子菜单
            menu.setChildren(list.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));

        }
        return Result.success(parentNode);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {   // restful
        return Result.success(menuService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {   // restful
        return Result.success(menuService.removeByIds(ids));
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {

        return Result.success(menuService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
//        queryWrapper.orderByDesc("id");
        return Result.success(menuService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}

