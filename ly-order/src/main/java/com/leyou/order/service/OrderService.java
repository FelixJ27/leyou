package com.leyou.order.service;

import com.leyou.order.dto.OrderDTO;

/**
 * @Author: Felix
 * @Description:
 */
public interface OrderService {
    Long createOrder(OrderDTO orderDTO);
}
