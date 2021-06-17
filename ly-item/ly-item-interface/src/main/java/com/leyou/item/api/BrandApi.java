package com.leyou.item.api;

import com.leyou.item.pojo.TbBrand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: Felix
 * @Description:
 */
public interface BrandApi {
    @GetMapping("brand/{id}")
    TbBrand queryBrandById(@PathVariable("id") Long id);

    @GetMapping("brand/list")
    List<TbBrand> queryBrandByIds(@RequestParam("ids") List<Long> ids);
}
