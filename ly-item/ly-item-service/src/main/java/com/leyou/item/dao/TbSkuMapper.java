package com.leyou.item.dao;

import com.leyou.item.pojo.TbSku;

public interface TbSkuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbSku record);

    int insertSelective(TbSku record);

    TbSku selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbSku record);

    int updateByPrimaryKey(TbSku record);
}