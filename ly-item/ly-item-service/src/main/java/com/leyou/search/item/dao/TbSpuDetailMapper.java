package com.leyou.search.item.dao;

import com.leyou.search.item.pojo.TbSpuDetail;

public interface TbSpuDetailMapper {
    int deleteByPrimaryKey(Long spuId);

    int insert(TbSpuDetail record);

    int insertSelective(TbSpuDetail record);

    TbSpuDetail selectByPrimaryKey(Long spuId);

    int updateByPrimaryKeySelective(TbSpuDetail record);

    int updateByPrimaryKeyWithBLOBs(TbSpuDetail record);

    int updateByPrimaryKey(TbSpuDetail record);
}