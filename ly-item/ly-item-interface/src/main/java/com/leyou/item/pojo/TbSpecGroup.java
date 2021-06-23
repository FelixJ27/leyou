package com.leyou.item.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author Felix
 * @Description 规格参数的分组
 */
@Data
public class TbSpecGroup {
    /**
     * 主键
     */
    private Long id;
    /**
     * 商品分类id,一个分类下有多个规格组
     */
    private Long cid;
    /**
     * 规格组的名称
     */
    private String name;

    private List<TbSpecParam> params;
}