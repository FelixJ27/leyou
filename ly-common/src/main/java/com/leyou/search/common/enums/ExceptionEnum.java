package com.leyou.search.common.enums;

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
    INVALID_FILE_TYPE(400, "无效的文件"),
    BRAND_NOT_FOUND(404, "品牌不存在"),
    CATEGORY_NOT_FOUND(404, "商品分类未找到"),
    SPEC_GROUP_NOT_FOUND(404, "商品规格组不存在"),
    SPEC_PARAM_NOT_FOUND(404, "商品规格参数不存在"),
    GOODS_NOT_FOUND(404, "商品不存在"),
    GOODS_SKU_NOT_FOUND(404, "商品sku不存在"),
    GOODS_STOCK_NOT_FOUND(404, "商品库存不存在"),
    GOODS_DETAIL_NOT_FOUND(404, "商品详情不存在"),
    BRAND_SAVE_ERROR(500, "新增品牌失败"),
    SPEC_PARAM_SAVE_FAIL(500, "新增规格参数信息失败"),
    UPDATE_SPEC_PARAM_FAIL(500, "更新规格参数信息失败"),
    UPLOAD_FILE_FAIL(500, "文件上传失败"),
    DELETE_SPEC_PARAM_FAIL(500, "删除规格参数信息失败"),
    GOODS_SAVE_ERROR(500, "新增商品失败"),
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
