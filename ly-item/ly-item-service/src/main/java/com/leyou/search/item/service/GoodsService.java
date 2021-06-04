package com.leyou.search.item.service;

import com.leyou.search.common.vo.PageResult;
import com.leyou.search.item.pojo.TbSku;
import com.leyou.search.item.pojo.TbSpu;
import com.leyou.search.item.pojo.TbSpuDetail;
import com.leyou.search.item.pojo.vo.SpuVo;

import java.util.List;

/**
 * @Author Felix
 * @Description 商品Service
 */
public interface GoodsService {
    /**
     * @description: 分页模查商品
     * @auther: Felix
     */
    PageResult<SpuVo> querySpuByPage(Integer page, String title, Boolean saleable, Integer rows);

    void saveGoods(TbSpu spu);

    TbSpuDetail queryDetailById(Long spuId);

    /**
     * @Author Felix
     * @Description 根据spu查询所有sku
     * @Param
     * @Return
     */
    List<TbSku> querySkuBySpuId(Long id);
}
