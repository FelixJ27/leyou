package com.leyou.item.service.impl;

import com.leyou.common.dto.CartDTO;
import com.leyou.item.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: Felix
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceImplTest {

    @Autowired
    private GoodsService goodsService;

    @Test
    public void decreaseStock() {
        List<CartDTO> cartDTOS = Arrays.asList(new CartDTO(2600242L, 2), new CartDTO(2600248L, 2));
        goodsService.decreaseStock(cartDTOS);
    }
}