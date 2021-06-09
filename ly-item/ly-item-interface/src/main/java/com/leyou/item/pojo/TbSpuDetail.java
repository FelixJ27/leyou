package com.leyou.item.pojo;

import lombok.Data;

/**
 * @Author Felix
 * @Description 商品详情信息
 */
@Data
public class TbSpuDetail {
    /**
     * spuId
     */
    private Long spuId;
    /**
     * 全部规格参数数据
     */
    private String genericSpec;
    /**
     * 特有规格参数及可选值信息
     */
    private String specialSpec;
    /**
     * 包装清单
     */
    private String packingList;
    /**
     * 售后服务
     */
    private String afterService;
    /**
     * 商品描述信息
     */
    private String description;

}