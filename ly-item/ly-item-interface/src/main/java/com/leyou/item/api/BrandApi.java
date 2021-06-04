package com.leyou.item.api;

import com.leyou.search.item.pojo.TbBrand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: Felix
 * @Description:
 */
public interface BrandApi {
    @GetMapping("/brand/{id}")
    TbBrand queryBrandById(@PathVariable("id") Long id);
}
