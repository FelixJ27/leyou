package com.leyou.common.advice;

import com.leyou.common.exception.LyException;
import com.leyou.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author Felix
 * @Description 异常处理通知类
 * @Date 2019/12/31 11:36
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(LyException.class)
    public ResponseEntity<ExceptionResult> handleException(LyException e) {
        return ResponseEntity.status(e.getExceptionEnum().getCode()).body(new ExceptionResult(e.getExceptionEnum()));
    }
}
