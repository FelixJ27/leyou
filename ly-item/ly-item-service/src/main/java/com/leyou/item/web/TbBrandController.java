package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.TbBrand;
import com.leyou.item.service.TbBrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Felix
 * @Description 品牌controller
 * @Date 2020/3/2 14:52
 * 面向对象面向卿，不负代码不负君
 */
@RestController
@RequestMapping("brand")
public class TbBrandController {

    @Resource
    private TbBrandService tbBrandService;

    /**
     * @description: 分页查询品牌
     * @auther: Felix
     * @date: 2020/3/2 15:05
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<TbBrand>> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key
    ) {
        return ResponseEntity.ok(tbBrandService.queryBrandByPage(page, rows,sortBy, desc,key));
    }

    @PostMapping()
    public ResponseEntity<Void> saveBrand(TbBrand brand, @RequestParam("cids")List<Long> cids){
        tbBrandService.saveBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
