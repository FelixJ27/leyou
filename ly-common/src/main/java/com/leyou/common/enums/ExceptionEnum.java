package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: Felix
 * @Description: 异常枚举
 * @Date: 2020/1/2 23:48
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {
    BRAND_NOT_FOUND(404, "品牌不存在"),
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
