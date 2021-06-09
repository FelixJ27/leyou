package com.leyou.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Author Felix
 * @Description 抽象商品
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbSpu {
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
     * 一个商品对应一个品牌
     */
    private TbBrand brand;

    private TbSpuDetail spuDetail;

    private List<TbSku> skus;

    public TbSpu(Long id, String title, String subTitle, Long cid1, Long cid2, Long cid3, Long brandId, Boolean saleable, Boolean valid, Date createTime, Date lastUpdateTime) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.cid1 = cid1;
        this.cid2 = cid2;
        this.cid3 = cid3;
        this.brandId = brandId;
        this.saleable = saleable;
        this.valid = valid;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
    }
}