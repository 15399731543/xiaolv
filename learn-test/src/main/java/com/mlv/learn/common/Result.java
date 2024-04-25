package com.mlv.learn.common;

import com.mlv.learn.exception.BusinessException;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author xiaolv
 * @since 2024-04-15 20:21:42
 * @param <T>
 */
@Data
public class Result<T> implements Serializable{


    private static final long serialVersionUID = 2058432196999941081L;

    private int code;

    private String message;

    private T records;


    public  Result<T> success (T t){
        Result<T> result = new Result<>();
        result.setCode(ResponseCode.SUCCESS);
        result.setMessage(ResponseCode.SUCCESS_MESSAGE);
        result.setRecords(t);
        return result;
    }

    public Result failed(Throwable e) {
        Result<T> result = new Result<>();
        if (e instanceof BusinessException) {
            result.setMessage(e.getMessage());
            result.setCode( ResponseCode.BUSINESS_FAIL);
        } else {
            result.setMessage(e.getMessage());
            result.setCode( ResponseCode.SYSTEM_FAIL);
        }
        return result;
    }

    public  Result<T> success (T t,String message){
        Result<T> result = new Result<>();
        result.setCode(ResponseCode.SUCCESS);
        result.setMessage(message);
        result.setRecords(t);
        return result;
    }


    public Result<T> failed (T t){
        Result<T> result = new Result<>();
        result.setCode(ResponseCode.FAILED);
        result.setMessage(ResponseCode.FAILED_MESSAGE);
        result.setRecords(t);
        return result;
    }

    public Result<T> failed (T t,String message){
        Result<T> result = new Result<>();
        result.setCode(ResponseCode.FAILED);
        result.setMessage(message);
        result.setRecords(t);
        return result;
    }
}
