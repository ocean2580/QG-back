package com.ocean.service;

import com.ocean.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ocean
 * @since 2022-07-31
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
