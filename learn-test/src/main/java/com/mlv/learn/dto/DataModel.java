package com.mlv.learn.dto;

import lombok.Data;

/**
 * 指标数据
 */
@Data
public class DataModel {
    private String organizationId;
    private String organizationName;
    private String dateTime;
    private String content;
}
