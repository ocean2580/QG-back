package com.ocean.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.common.Constants;
import com.ocean.common.Result;
import com.ocean.entity.Dict;
import com.ocean.entity.Menu;
import com.ocean.entity.Role;
import com.ocean.mapper.DictMapper;
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

    @Resource
    private DictMapper dictMapper;

    @PostMapping
    public Result save(@RequestBody Menu menu) {   // 前端传来的json数据映射成对象
        // 新增|更新
        return Result.success(menuService.saveOrUpdate(menu));
    }

    @GetMapping("/ids")
    public Result findAllIds() {
        return Result.success(menuService.list().stream().map(Menu::getId));
    }

    @GetMapping
    public Result findAll(@RequestParam(defaultValue = "") String name) {

        return Result.success(menuService.findMenus(name));
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

    @GetMapping("/icons")
    public Result getIcons() {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<Dict>();
        queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
        return Result.success(dictMapper.selectList(queryWrapper));
    }
}

