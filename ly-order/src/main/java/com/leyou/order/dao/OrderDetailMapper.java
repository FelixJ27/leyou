package com.leyou.order.dao;

import com.leyou.order.pojo.OrderDetail;

import java.util.List;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    int insertListSelective(List<OrderDetail> orderDetails);

    OrderDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
}