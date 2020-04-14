package com.leyou.item.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author Felix
 * @Description 商品实体
 */
@Data
public class TbSku {
    /**
     * 主键
     */
    private Long id;
    /**
     * spuId
     */
    private Long spuId;
    /**
     * 标题
     */
    private String title;
    /**
     * 图片
     */
    private String images;
    /**
     * 销售价格
     */
    private Long price;
    /**
     * 特有规格属性在spu属性模板中的对应下标组合
     */
    private String indexes;
    /**
     * sku的特有规格参数键值对，json格式，反序列化时请使用linkedHashMap，保证有序
     */
    private String ownSpec;
    /**
     * 是否有效，0无效，1有效
     */
    private Boolean enable;
    /**
     * 添加时间
     */
    private Date createTime;
    /**
     * 最后修改时间
     */
    private Date lastUpdateTime;

    private Integer stock;

}