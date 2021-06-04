package com.leyou.search.common.vo;

import com.leyou.search.common.enums.ExceptionEnum;
import lombok.Data;

/**
 * @Author: Felix
 * @Description: 异常结果类
 * @Date: 2020/1/2 23:57
 */
@Data
public class ExceptionResult {
    /**
     * 状态码
     */
    private int status;
    /**
     * 消息
     */
    private String message;
    /**
     * 时间戳
     */
    private Long timeStamp;

    public ExceptionResult(ExceptionEnum em) {
        this.status = em.getCode();
        this.message = em.getMsg();
        this.timeStamp = System.currentTimeMillis();
    }
}
