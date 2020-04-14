package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.TbSpu;
import com.leyou.item.pojo.vo.SpuVo;
import com.leyou.item.service.GoodsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

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
     * @Author Felix J
     * @Description 商品新增
     */
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody TbSpu spu) {
        goodsService.saveGoods(spu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
