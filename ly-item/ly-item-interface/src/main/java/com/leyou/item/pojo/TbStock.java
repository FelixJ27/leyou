package com.leyou.item.pojo;

import lombok.Data;
/**
 * @Author Felix J
 * @Description 库存
 */
@Data
public class TbStock {
    private Long skuId;

    private Integer seckillStock;

    private Integer seckillTotal;

    private Integer stock;

}