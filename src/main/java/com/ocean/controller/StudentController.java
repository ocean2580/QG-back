package com.ocean.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.common.Result;
import com.ocean.entity.Student;
import com.ocean.entity.User;
import com.ocean.service.impl.StudentServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentServiceImpl studentService;

    @GetMapping
    public Result findAll() {
        return Result.success(studentService.list());
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {   // restful
        return Result.success(studentService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(studentService.removeByIds(ids));
    }

    @GetMapping("/name/{studentName}")
    public Result findOne(@PathVariable String studentName) {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.eq("studentName",studentName);
        return Result.success(studentService.getOne(qw));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam("pageNum") Integer num,
                           @RequestParam("pageSize") Integer size,
                           @RequestParam(value = "name", defaultValue = "") String name,
                           @RequestParam(value = "institute", defaultValue = "") String institute) {
        IPage<Student> page = new Page<>(num, size);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        // and 连接
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        if (!"".equals(institute)) {
            queryWrapper.like("institute", institute);
        }
//        queryWrapper.orderByDesc("id");
        return Result.success(studentService.page(page, queryWrapper));
    }

    @PostMapping
    public Result save(@RequestBody Student student) {   // 前端传来的json数据映射成对象
        // 新增|更新
        return Result.success(studentService.saveStudent(student));
    }

}
