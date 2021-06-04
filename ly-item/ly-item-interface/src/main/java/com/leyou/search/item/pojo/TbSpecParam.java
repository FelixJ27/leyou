package com.leyou.search.item.pojo;

import lombok.Data;

/**
 * @Author Felix
 * @Description 规格参数租下的参数名
 */
@Data
public class TbSpecParam {
    private Long id;

    private Long cid;

    private Long groupId;

    private String name;

    private Boolean numeric;

    private String unit;

    private Boolean generic;

    private Boolean searching;

    private String segments;

}