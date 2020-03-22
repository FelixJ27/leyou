package com.leyou.item.service;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.vo.SpuVo;

/**
 * @Author Felix
 * @Description 商品Service
 */
public interface GoodsService {
    /**
     * @description: 分页模查商品
     * @auther: Felix
     */
    PageResult<SpuVo> querySpuByPage(Integer page, String title, Boolean saleable, Integer rows);
}
