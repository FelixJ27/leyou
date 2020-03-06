package com.leyou.item.dao;

import com.leyou.item.pojo.TbSpecParam;

import java.util.List;

public interface TbSpecParamMapper {

    /**
     * 查询规格参数组下的参数名
     * @param gid
     * @return
     */
    List<TbSpecParam> queryParamByGid(Long gid);

    int deleteByPrimaryKey(Long id);

    int insert(TbSpecParam record);

    int insertSelective(TbSpecParam record);

    TbSpecParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbSpecParam record);

    int updateByPrimaryKey(TbSpecParam record);
}