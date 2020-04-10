package com.leyou.item.web;

import com.leyou.item.pojo.TbCategory;
import com.leyou.item.service.TbCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Felix J
 * @Description
 * @Date 2020/1/14 17:18
 * 面向对象面向卿，不负代码不负君
 */
@RestController
@RequestMapping("category")
public class CategoryController {
    @Resource
    private TbCategoryService tbCategoryService;

    /**
     * @description: 父id查询商品种类
     * @auther: Felix J
     * @date: 2020/1/14 17:45
     */
    @GetMapping("list")
    public ResponseEntity<List<TbCategory>> queryCategoryListByPid(@RequestParam("pid")Long pid){
        return ResponseEntity.ok(tbCategoryService.queryCategoryListByPid(pid));
    }


}
