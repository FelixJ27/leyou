package com.leyou.search.common.exception;

import com.leyou.search.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: Felix
 * @Description: 自定义异常类
 * @Date: 2020/1/2 23:31
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LyException extends RuntimeException {

    private ExceptionEnum exceptionEnum;
}
