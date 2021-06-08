package com.leyou.item.dao;

import com.leyou.item.pojo.TbSpecGroup;
import com.leyou.item.pojo.TbSpecParam;

import java.util.List;

public interface TbSpecGroupMapper {
    /**
     * 查询规格参数组
     * @param cid
     * @return
     */
    List<TbSpecGroup> queryGroupByCid(Long cid);

    /**
     * 参训参数组下的参数名
     * @param gid
     * @return
     */
    List<TbSpecParam> queryParamByCid(Long gid);

    int deleteByPrimaryKey(Long id);

    int insert(TbSpecGroup record);

    int insertSelective(TbSpecGroup record);

    TbSpecGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbSpecGroup record);

    int updateByPrimaryKey(TbSpecGroup record);
}