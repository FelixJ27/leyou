package com.leyou.item.dao;

import com.leyou.item.pojo.TbStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbStockMapper {
    int deleteByPrimaryKey(Long skuId);

    int insert(TbStock record);

    int insertSelective(TbStock record);

    /**
     * @Author: Felix
     * @Description: 批量新增商品
     * @Date: 17:40 2021/5/3
     */
    int batchInsert(List<TbStock> stockList);

    TbStock selectByPrimaryKey(Long skuId);

    int updateByPrimaryKeySelective(TbStock record);

    int updateByPrimaryKey(TbStock record);

   /**
    * @Author Felix
    * @Description 根据id集合查询库存集合
    * @Param
    * @Return
    */
    List<TbStock> selectByIdList(List<Long> ids);

    int deleteByIds(List<Long> ids);

    /**
     * @Author Felix
     * @Description 减库存
     */
    int decreaseStock(@Param("id") Long id,@Param("num") Integer num);
}