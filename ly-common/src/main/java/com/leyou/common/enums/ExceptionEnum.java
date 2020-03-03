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
    BRAND_SAVE_ERROR(500, "新增品牌失败"),
    UPLOAD_FILE_FAIL(500, "文件上传失败"),
    INVALID_FILE_TYPE(400, "无效的文件")
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
