package com.leyou.item.web;

import com.leyou.item.pojo.TbSpecGroup;
import com.leyou.item.service.SpecificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Felix
 * @Description 规格参数controller
 */
@RestController
@RequestMapping("spec")
public class SpecificationController {
    @Resource
    private SpecificationService specificationService;

    /**
     * @description: 根据商品种类id查询规格参数
     * @auther: Felix
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<TbSpecGroup>> queryGroupByCid(@PathVariable("cid")Long cid) {
        return ResponseEntity.ok(specificationService.queryGroupByCid(cid));
    }
}
