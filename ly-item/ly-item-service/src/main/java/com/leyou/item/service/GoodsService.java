package com.leyou.item.service;

import com.leyou.common.dto.CartDTO;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.TbSku;
import com.leyou.item.pojo.TbSpu;
import com.leyou.item.pojo.TbSpuDetail;
import com.leyou.item.pojo.vo.SpuVo;

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

    TbSpu querySpuById(Long id);

    void updateGoods(TbSpu spu);

    List<TbSku> querySkuByIds(List<Long> ids);

    void decreaseStock(List<CartDTO> cartDTOS);
}
