package com.leyou.item.dao;

import com.leyou.item.pojo.TbSpecGroup;

import java.util.List;

public interface TbSpecGroupMapper {

    List<TbSpecGroup> queryGroupByCid(Long cid);

    int deleteByPrimaryKey(Long id);

    int insert(TbSpecGroup record);

    int insertSelective(TbSpecGroup record);

    TbSpecGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbSpecGroup record);

    int updateByPrimaryKey(TbSpecGroup record);
}