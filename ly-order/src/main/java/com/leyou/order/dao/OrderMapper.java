package com.leyou.order.dao;

import com.leyou.order.pojo.Order;

public interface OrderMapper {
    /**
     * 根据orderId查询Order和OrderDetail
     * @param id
     * @return
     */
    Order selectByOrderId(Long id);

    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}