package com.leyou.item.api;

import com.leyou.common.dto.CartDTO;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.TbSku;
import com.leyou.item.pojo.TbSpu;
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

    @GetMapping("spu/{id}")
    TbSpu querySpuById(@PathVariable("id") Long id);

    @GetMapping("/sku/list/ids")
    List<TbSku> querySkuByIds(@RequestParam("ids") List<Long> ids);

    /**
     * @Author Felix
     * @Description 减库存
     */
    @PostMapping("stock/decrease")
    Void decreaseStock(@RequestBody List<CartDTO> cartDTOS);
}
