package com.leyou.item.web;

import com.leyou.common.dto.CartDTO;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.TbSku;
import com.leyou.item.pojo.TbSpu;
import com.leyou.item.pojo.TbSpuDetail;
import com.leyou.item.pojo.vo.SpuVo;
import com.leyou.item.service.GoodsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Felix
 * @Description 商品controller
 */
@RestController
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @GetMapping("spu/page")
    public ResponseEntity<PageResult<SpuVo>> querySpuByPage(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "key", required = false) String key,
            @RequestParam(name = "saleable", required = false) Boolean saleable,
            @RequestParam(name = "rows", defaultValue = "5") Integer rows
    ) {
        return ResponseEntity.ok(goodsService.querySpuByPage(page, key, saleable, rows));
    }

    /**
     * @Author Felix
     * @Description 商品新增
     */
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody TbSpu spu) {
        goodsService.saveGoods(spu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * @Author Felix
     * @Description 根据spu的id查询详情
     * @Param
     * @Return
     */
    @GetMapping("/spu/detail/{id}")
    public ResponseEntity<TbSpuDetail> queryDetailById(@PathVariable("id") Long spuId) {
        return ResponseEntity.ok(goodsService.queryDetailById(spuId));
    }

    /**
     * @Author Felix
     * @Description 查询spu下所有的sku
     * @Param
     * @Return
     */
    @GetMapping("/sku/list")
    public ResponseEntity<List<TbSku>> querySkuBySpuId(@RequestParam("id") Long id) {
        return ResponseEntity.ok(goodsService.querySkuBySpuId(id));
    }

    @GetMapping("/sku/list/ids")
    public ResponseEntity<List<TbSku>> querySkuByIds(@RequestParam("ids") List<Long> ids) {
        return ResponseEntity.ok(goodsService.querySkuByIds(ids));
    }

    @GetMapping("spu/{id}")
    public ResponseEntity<TbSpu> querySpuById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(goodsService.querySpuById(id));
    }

    @PutMapping("goods")
    public ResponseEntity<Void> updateGoods(@RequestBody TbSpu spu) {
        goodsService.updateGoods(spu);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * @Author Felix
     * @Description 减库存
     */
    @PostMapping("stock/decrease")
    public ResponseEntity<Void> decreaseStock(@RequestBody List<CartDTO> cartDTOS) {
        goodsService.decreaseStock(cartDTOS);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
