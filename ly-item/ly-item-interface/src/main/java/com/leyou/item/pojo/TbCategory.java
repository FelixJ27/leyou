package com.leyou.item.pojo;

import lombok.Data;

/**
 * @Author Felix
 * @Description 商品种类
 * @Date 2020/1/14 17:06
 * 面向对象面向卿，不负代码不负君
 */
@Data
//@Table(name = "tb_category")
public class TbCategory {
    /*@Id
    @KeySql(useGeneratedKeys = true)*/
    private Long id;
    /**
     * 种类名称
     */
    private String name;
    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 是否为父级
     */
    private Boolean isParent;
    /**
     * 排序指数
     */
    private Integer sort;
}