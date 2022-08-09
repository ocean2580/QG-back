package com.ocean.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.common.Result;
import com.ocean.entity.Goods;
import com.ocean.entity.Student;
import com.ocean.service.impl.GoodsServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsServiceImpl goodsService;

    @GetMapping
    public Result findAll() {
        return Result.success(goodsService.list());
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {   // restful
        return Result.success(goodsService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(goodsService.removeByIds(ids));
    }

    @GetMapping("/goodsName/{goodsName}")
    public Result findOne(@PathVariable String goodsName) {
        QueryWrapper<Goods> qw = new QueryWrapper<>();
        qw.eq("lost_name",goodsName);
        return Result.success(goodsService.getOne(qw));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam("pageNum") Integer num,
                           @RequestParam("pageSize") Integer size,
                           @RequestParam(value = "goodsName", defaultValue = "") String name,
                           @RequestParam(value = "lostPosition", defaultValue = "") String lostPosition,
                           @RequestParam(value = "claimPosition", defaultValue = "") String claimPosition) {
        IPage<Goods> page = new Page<>(num, size);
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        // and 连接
        if (!"".equals(name)) {
            queryWrapper.like("lost_name", name);
        }
        if (!"".equals(lostPosition)) {
            queryWrapper.like("lost_position", lostPosition);
        }
        if (!"".equals(claimPosition)) {
            queryWrapper.like("claim_position", claimPosition);
        }
//        queryWrapper.orderByDesc("id");
        return Result.success(goodsService.page(page, queryWrapper));
    }

    @PostMapping
    public Result save(@RequestBody Goods goods) {   // 前端传来的json数据映射成对象
        // 新增|更新
        return Result.success(goodsService.saveGoods(goods));
    }

}
