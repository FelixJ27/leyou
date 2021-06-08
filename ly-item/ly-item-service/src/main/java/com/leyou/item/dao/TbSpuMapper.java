package com.leyou.item.dao;

import com.leyou.item.pojo.TbSpu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbSpuMapper {

    /**
     * @description: 分页模查商品
     * @auther: Felix
     */
    List<TbSpu> findByPage(@Param(value="title")String title);

    int deleteByPrimaryKey(Long id);

    int insert(TbSpu record);

    int insertSelective(TbSpu record);

    TbSpu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbSpu record);

    int updateByPrimaryKey(TbSpu record);
}