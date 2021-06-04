package com.leyou.search.item.dao;


import com.leyou.search.item.pojo.TbCategory;

import java.util.List;

public interface TbCategoryMapper {
    List<TbCategory> select(TbCategory tbCategory);
    /**
     * @description: 通过id集合查询分类
     * @auther: Felix
     */
    List<TbCategory> selectByIdList(List<Long> ids);

    int deleteByPrimaryKey(Long id);

    int insert(TbCategory record);

    int insertSelective(TbCategory record);

    TbCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbCategory record);

    int updateByPrimaryKey(TbCategory record);
}