package com.leyou.order.web;

import com.leyou.order.dto.OrderDTO;
import com.leyou.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Felix
 * @Description:
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.createOrder(orderDTO));
    }

}
