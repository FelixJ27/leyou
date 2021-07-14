package com.leyou.cart.service;

import com.leyou.cart.pojo.Cart;

import java.util.List;

/**
 * @Author: Felix
 * @Description:
 */
public interface CartService {
    /**
     * 新增购物车
     * @param cart
     */
    void addCart(Cart cart);

    /**
     * @Author Felix
     * @Description 查询购物车
     */
    List<Cart> queryCarts();

    void updateCartNum(Long skuId, Integer num);

    void delCart(Long skuId);
}
