package com.leyou.page.client;

import com.leyou.item.api.SpecificationApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: Felix
 * @Description:
 */
@FeignClient("item-service")
public interface SpecificationClient extends SpecificationApi {
}
