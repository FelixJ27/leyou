package com.leyou.order.service;

import com.leyou.order.dto.OrderDTO;
import com.leyou.order.pojo.Order;

/**
 * @Author: Felix
 * @Description:
 */
public interface OrderService {
    Long createOrder(OrderDTO orderDTO);

    Order queryOrderById(Long id);
}
