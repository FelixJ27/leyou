package com.leyou.item.dao;

import com.leyou.item.pojo.TbBrand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbBrandMapper {
    /**
     * 分页查询品牌
     */
    List<TbBrand> queryBrandByPage(TbBrand tbBrand);
    /**
     * 新增品牌
     * @param tbBrand
     */
    int saveBrand(TbBrand tbBrand);

    /**
     * 中间表新增
     * @param categoryId
     * @param brandId
     * @return
     */
    int insertCategoryBrand(Long categoryId, Long brandId);

    /**
     * 通过cid查询品牌
     * @param cid
     * @return
     */
    List<TbBrand> queryByCategoryId(@Param(value = "cid") Long cid);

    int deleteByPrimaryKey(Long id);

    int insert(TbBrand record);

    int insertSelective(TbBrand record);

    TbBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbBrand record);

    int updateByPrimaryKey(TbBrand record);
}