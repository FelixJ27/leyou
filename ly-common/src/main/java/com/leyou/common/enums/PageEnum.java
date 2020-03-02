package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Author Felix
 * @Description
 * @Date 2020/3/2 14:58
 * 面向对象面向卿，不负代码不负君
 */
@NoArgsConstructor
@AllArgsConstructor
public enum PageEnum {
    PAGE_ROWS(5),
    ;

    private int pagesize;
}
