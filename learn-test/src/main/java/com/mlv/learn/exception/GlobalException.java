package com.mlv.learn.exception;

import com.mlv.learn.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author xiaolv
 * @since 2024-04-15 20:21:42
 */
@RestControllerAdvice
public class GlobalException {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    /**
     * 全局异常处理，统一api返回结果
     *
     * @param e 异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleValidationBodyException(Exception e) {
        e.printStackTrace();
        return new Result().failed(e);
    }
}
