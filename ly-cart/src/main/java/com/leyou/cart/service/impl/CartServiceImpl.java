package com.leyou.cart.service.impl;

import com.leyou.cart.interceptor.UserInterceptor;
import com.leyou.cart.pojo.Cart;
import com.leyou.cart.service.CartService;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundGeoOperations;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author: Felix
 * @Description:
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    private static final String KEY_PREFIX = "cart：uid:";

    @Override
    public void addCart(Cart cart) {
        //获取登陆的用户
        UserInfo user = UserInterceptor.getUser();
        Integer num = cart.getNum();
        String key = KEY_PREFIX + user.getId();
        //hashKey
        String hashKey = cart.getSkuId().toString();
        BoundHashOperations<String, Object, Object> operation = redisTemplate.boundHashOps(key);
        //判断当前购物车商品是否存在，是：修改数量；否：新增
        if (operation.hasKey(hashKey)) {
            String json = operation.get(hashKey).toString();
            cart = JsonUtils.parse(json, Cart.class);
            cart.setNum(cart.getNum() + num);
        }
        //写回redis
        operation.put(hashKey, JsonUtils.serialize(cart));
    }

    @Override
    public List<Cart> queryCarts() {
        //获取登陆的用户
        UserInfo user = UserInterceptor.getUser();
        String key = KEY_PREFIX + user.getId();
        if (!redisTemplate.hasKey(key)) {
            throw new LyException(ExceptionEnum.CART_NOT_FOUND);
        }
        BoundHashOperations<String, Object, Object> operation = redisTemplate.boundHashOps(key);
        List<Cart> carts = operation.values().stream().map(o -> JsonUtils.parse(o.toString(), Cart.class))
                .collect(Collectors.toList());
        return carts;
    }

    @Override
    public void updateCartNum(Long skuId, Integer num) {
        UserInfo user = UserInterceptor.getUser();
        String key = KEY_PREFIX + user.getId();
        String hashKey = skuId.toString();
        BoundHashOperations<String, Object, Object> operations = redisTemplate.boundHashOps(key);

        if (!operations.hasKey(hashKey)) {
            throw new LyException(ExceptionEnum.CART_NOT_FOUND);
        }

        Cart cart = JsonUtils.parse(operations.get(skuId.toString()).toString(), Cart.class);
        cart.setNum(num);

        operations.put(hashKey, JsonUtils.serialize(cart));
    }


    @Override
    public void delCart(Long skuId) {
        UserInfo user = UserInterceptor.getUser();
        String key = KEY_PREFIX + user.getId();
        redisTemplate.opsForHash().delete(key, skuId.toString());
    }
}
