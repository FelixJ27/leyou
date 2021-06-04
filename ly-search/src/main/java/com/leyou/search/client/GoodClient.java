package com.leyou.search.client;

import com.leyou.item.api.GoodApi;
import com.leyou.search.item.pojo.TbSpu;
import com.leyou.search.item.pojo.vo.SpuVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Felix
 * @Description:
 */
@FeignClient("item-service")
public interface GoodClient extends GoodApi {

}
