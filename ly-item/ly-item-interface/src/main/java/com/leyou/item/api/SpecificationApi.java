package com.leyou.item.api;

import com.leyou.search.item.pojo.TbSpecParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: Felix
 * @Description:
 */
public interface SpecificationApi {
    @GetMapping("/spec/params")
    List<TbSpecParam> queryParamList(@RequestParam(value = "gid", required = false) Long gid,
                                     @RequestParam(value = "cid", required = false) Long cid);
}
