package com.leyou.item.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author Felix
 * @Description 抽象商品vo
 */
@Data
public class SpuVo {
    /**
     * 主键
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 子标题
     */
    private String subTitle;
    /**
     * 1级类目id
     */
    private Long cid1;
    /**
     * 2级类目id
     */
    private Long cid2;
    /**
     * 3级类目id
     */
    private Long cid3;
    /**
     * 商品所属品牌id
     */
    private Long brandId;
    /**
     * 是否上架，0下架，1上架
     */
    private Boolean saleable;
    /**
     * 是否有效，0已删除，1有效
     */
    private Boolean valid;
    /**
     * 添加时间
     */
    private Date createTime;
    /**
     * 最后修改时间
     */
    private Date lastUpdateTime;
    /**
     * 商品种类名称
     */
    private String cName;
    /**
     * 商品名称
     */
    private String bName;
}
