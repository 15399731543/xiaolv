package com.mlv.learn.exception;

/**
 * 业务异常
 *
 * @author xiaolv
 * @since 2024-04-15 20:21:42
 */
public class BusinessException extends RuntimeException {

    static final long serialVersionUID = -6034847190745766945L;
    public BusinessException() {
        super();
    }
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Exception e) {
        super(e);
    }

    public BusinessException(String message,Exception e) {
        super(message,e);
    }

}
