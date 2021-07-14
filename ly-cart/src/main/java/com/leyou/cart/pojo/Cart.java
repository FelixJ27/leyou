package com.leyou.cart.pojo;

import lombok.Data;

/**
 * @Author: Felix
 * @Description:
 */
@Data
public class Cart {
    private Long skuId;
    private String title;
    private String image;
    private Long  price;
    private Integer num;
    private String ownSpec;
}
