package com.leyou.item.web;

import com.leyou.item.pojo.TbSpecGroup;
import com.leyou.item.pojo.TbSpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * @description: 根据商品组id查询规格参数的参数名
     * @auther: Felix
     */
    @GetMapping("params")
    public ResponseEntity<List<TbSpecParam>> queryParamByCid(@RequestParam("gid") Long gid) {
        return ResponseEntity.ok(specificationService.queryParamByGid(gid));
    }

    /**
     * @description: 新增规格参数组下参数名
     * @auther: Felix
     */
    @PostMapping("param")
    public ResponseEntity<Void> addSpecParam(@RequestBody TbSpecParam tbSpecParam) {
        specificationService.addSpecParam(tbSpecParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * @description: 更新参数名信息
     * @auther: Felix
     */
    @PutMapping("param")
    public ResponseEntity<Void> updateSpecParam(@RequestBody TbSpecParam tbSpecParam) {
        specificationService.updateSpecParam(tbSpecParam);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("param/{id}")
    public ResponseEntity<Void> deleteSpecParam(@PathVariable Long id) {
        specificationService.deleteSpecParam(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
