package com.leyou.search.item.dao;

import com.leyou.search.item.pojo.TbSpecParam;

import java.util.List;

public interface TbSpecParamMapper {

    /**
     * 查询规格参数组下的参数名
     * @param specParam
     * @return
     */
    List<TbSpecParam> querySpecParamList(TbSpecParam specParam);

    int deleteByPrimaryKey(Long id);

    int insert(TbSpecParam record);

    int insertSelective(TbSpecParam record);

    TbSpecParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbSpecParam record);

    int updateByPrimaryKey(TbSpecParam record);
}