package com.ocean.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocean.entity.Goods;
import com.ocean.mapper.GoodsMapper;
import com.ocean.service.IGoodsService;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Override
    public Boolean saveGoods(Goods goods) {
        return saveOrUpdate(goods);
    }
}
