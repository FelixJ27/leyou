package com.leyou.item.dao;

import com.leyou.item.pojo.TbStock;

public interface TbStockMapper {
    int deleteByPrimaryKey(Long skuId);

    int insert(TbStock record);

    int insertSelective(TbStock record);

    TbStock selectByPrimaryKey(Long skuId);

    int updateByPrimaryKeySelective(TbStock record);

    int updateByPrimaryKey(TbStock record);
}