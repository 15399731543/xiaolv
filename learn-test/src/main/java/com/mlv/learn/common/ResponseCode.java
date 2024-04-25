package com.mlv.learn.common;

/**
 *
 * @author xiaolv
 * @since 2024-04-15 20:21:42
 */
public class ResponseCode {
    public static final int SUCCESS = 200;
    public static final String SUCCESS_MESSAGE = "操作成功";

    public static final int FAILED = 500;
    public static final String FAILED_MESSAGE = "操作失败,请重试";

    /**
     * 业务异常：失败
     */
    public static final int BUSINESS_FAIL = -2;

    public static final String BUSINESS_FAIL_MESSAGE = "业务异常";

    /**
     * 业务异常：失败
     */
    public static final int SYSTEM_FAIL = -1;

    /**
     * 默认成功信息
     */
    private String message = "success";

    /**
     * 默认成功编码
     */
    private int code = SUCCESS;
}
