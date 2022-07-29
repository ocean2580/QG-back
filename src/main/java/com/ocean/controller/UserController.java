package com.ocean.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.entity.User;
import com.ocean.mapper.UserMapper;
import com.ocean.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {   // restful
        return userService.removeByIds(ids);
    }


    /**
     * // @RequestParam 接受 ?pageNum=1&pageSize=10
     *
     * @param num  = (pageNum-1) * size
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
                                @RequestParam(value = "username", defaultValue = "") String name,
                                @RequestParam(value = "email", defaultValue = "") String email,
                                @RequestParam(value = "address", defaultValue = "") String address) {
        IPage<User> page = new Page<>(num, size);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // and 连接
        if (!"".equals(name)) {
            queryWrapper.like("username", name);
        }
        if (!"".equals(email)) {
            queryWrapper.like("email", email);
        }
        if (!"".equals(address)) {
            queryWrapper.like("address", address);
        }
//        queryWrapper.orderByDesc("id");
        return userService.page(page, queryWrapper);
    }


    /**
     * 导出用户信息
     */
    @SneakyThrows
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws UnsupportedEncodingException {
        List<User> list = userService.list();
        ExcelWriter writer = ExcelUtil.getWriter(true);

//        转中文
//        writer.addHeaderAlias("username", "用户名");
//        writer.addHeaderAlias("password", "密码");
//        writer.addHeaderAlias("email", "邮箱");
//        writer.addHeaderAlias("address", "地址");
//        writer.addHeaderAlias("nickname", "昵称");
//        writer.addHeaderAlias("phone", "电话");
//        writer.addHeaderAlias("createTime", "创建时间");
//        writer.addHeaderAlias("avatarUrl", "头像");

        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    /**
     * 导入
     */
    @SneakyThrows
    @PostMapping("/import")
    public void imp(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<User> users = reader.readAll(User.class);
        System.out.println(users);
        userService.saveOrUpdateBatch(users);
    }
}
