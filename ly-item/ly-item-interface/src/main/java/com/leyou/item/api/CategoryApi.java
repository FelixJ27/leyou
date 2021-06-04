package com.leyou.item.api;

import com.leyou.search.item.pojo.TbCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: Felix
 * @Description:
 */
public interface CategoryApi {
    /**
     * @Author Felix
     * @Description 根据id查询商品分类
     * @Param
     * @Return
     */
    @GetMapping("category/list/ids")
    List<TbCategory> queryCategoryByIds(@RequestParam("ids")List<Long> ids);
}
