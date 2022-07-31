package com.ocean.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.common.Constants;
import com.ocean.common.Result;
import com.ocean.controller.dto.UserDTO;
import com.ocean.entity.User;
import com.ocean.service.IUserService;
import com.ocean.service.impl.UserServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceImpl userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400,"参数错误");
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400,"参数错误");
        }
        return Result.success(userService.register(userDTO));
    }

    @PostMapping
    public Result save(@RequestBody User user) {   // 前端传来的json数据映射成对象
        // 新增|更新
        return Result.success(userService.saveUser(user));
    }

    @GetMapping
    public Result findAll() {
        return Result.success(userService.list());
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {   // restful
        return Result.success(userService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {   // restful
        return Result.success(userService.removeByIds(ids));
    }

    @GetMapping("/username/{username}")
    public Result findOne(@PathVariable String username) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username",username);
        return Result.success(userService.getOne(qw));
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
    public Result findPage(@RequestParam("pageNum") Integer num,
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
        return Result.success(userService.page(page, queryWrapper));
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
