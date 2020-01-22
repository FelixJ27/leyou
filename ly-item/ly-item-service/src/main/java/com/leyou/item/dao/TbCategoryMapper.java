package com.leyou.item.dao;


import com.leyou.item.pojo.TbCategory;

import java.util.List;

public interface TbCategoryMapper {
    List<TbCategory> select(TbCategory tbCategory);

    int deleteByPrimaryKey(Long id);

    int insert(TbCategory record);

    int insertSelective(TbCategory record);

    TbCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbCategory record);

    int updateByPrimaryKey(TbCategory record);
}