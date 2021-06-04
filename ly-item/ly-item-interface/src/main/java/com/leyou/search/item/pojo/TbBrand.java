package com.leyou.search.item.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author Felix
 * @Description 商品品牌
 * @Date 2020/1/14 17:06
 * 面向对象面向卿，不负代码不负君
 */
@Data
public class TbBrand {
    /**
     * 主键
     */
    private Long id;
    /**
     * 品牌名称
     */
    private String name;
    /**
     * 品牌图片地址
     */
    private String image;
    /**
     * 品牌的首字母
     */
    private String letter;
    /**
     * 一个品牌对应多个商品
     */
    private List<TbSpu> spuList;
}