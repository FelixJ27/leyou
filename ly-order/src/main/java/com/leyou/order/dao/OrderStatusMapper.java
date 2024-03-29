package com.leyou.order.dao;

import com.leyou.order.pojo.OrderStatus;

public interface OrderStatusMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(OrderStatus record);

    int insertSelective(OrderStatus record);

    OrderStatus selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(OrderStatus record);

    int updateByPrimaryKey(OrderStatus record);
}