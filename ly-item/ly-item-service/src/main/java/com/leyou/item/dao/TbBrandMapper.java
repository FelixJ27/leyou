package com.leyou.item.dao;

import com.leyou.item.pojo.TbBrand;
import java.util.List;

public interface TbBrandMapper {
    /**
     * 分页查询品牌
     */
    List<TbBrand> queryBrandByPage(TbBrand tbBrand);

    int deleteByPrimaryKey(Long id);

    int insert(TbBrand record);

    int insertSelective(TbBrand record);

    TbBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbBrand record);

    int updateByPrimaryKey(TbBrand record);
}