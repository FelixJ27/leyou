package com.leyou.order.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: Felix
 * @Description:
 */
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {
}
