package com.leyou.cart.web;

import com.leyou.cart.pojo.Cart;
import com.leyou.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Felix
 * @Description:
 */
@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping()
    public ResponseEntity<Void> addCart(@RequestBody Cart cart) {
        cartService.addCart(cart);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * @Author Felix
     * @Description 查询购物车
     */
    @GetMapping("list")
    public ResponseEntity<List<Cart>> queryCarts() {
        return ResponseEntity.ok(cartService.queryCarts());
    }

    @PutMapping
    public ResponseEntity<Void> updateCartNum(@RequestParam("id")Long skuId, @RequestParam("num")Integer num) {
        cartService.updateCartNum(skuId, num);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("{skuId}")
    public ResponseEntity<Void> delCart(@PathVariable("skuId")Long skuId) {
        cartService.delCart(skuId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
