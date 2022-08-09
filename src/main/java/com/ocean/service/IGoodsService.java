package com.ocean.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ocean.entity.Goods;

public interface IGoodsService extends IService<Goods> {
    Boolean saveGoods(Goods goods);
}
