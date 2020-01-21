package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: Felix J
 * @Description: 异常枚举
 * @Date: 2020/1/2 23:48
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {
    PRICE_CANNOT_BE_NULL(400, "价格不能为空！"),
    CATEGORY_NOT_FOUND(404, "商品分类未找到"),
    ;
    /**
     * 状态码
     */
    private int code;
    /**
     * 消息
     */
    private String msg;
}
