package com.leyou.item.api;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.TbSku;
import com.leyou.item.pojo.TbSpuDetail;
import com.leyou.item.pojo.vo.SpuVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Felix
 * @Description:
 */
public interface GoodsApi {
    @GetMapping("spu/page")
    PageResult<SpuVo> querySpuByPage(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "key", required = false) String key,
            @RequestParam(name = "saleable", required = false) Boolean saleable,
            @RequestParam(name = "rows", defaultValue = "5") Integer rows
    );

    /**
     * @Author Felix
     * @Description 查询spu下所有的sku
     * @Param
     * @Return
     */
    @GetMapping("/sku/list")
    List<TbSku> querySkuBySpuId(@RequestParam("id") Long id);

    /**
     * @Author Felix
     * @Description 根据spu的id查询详情
     * @Param
     * @Return
     */
    @GetMapping("/spu/detail/{id}")
    TbSpuDetail queryDetailById(@PathVariable("id") Long spuId);
}