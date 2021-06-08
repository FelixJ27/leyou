package com.leyou.client;

import com.leyou.item.api.GoodApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: Felix
 * @Description:
 */
@FeignClient("item-service")
public interface GoodsClient extends GoodApi {

}
