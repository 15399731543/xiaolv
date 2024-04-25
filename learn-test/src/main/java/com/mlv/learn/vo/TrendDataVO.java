package com.mlv.learn.vo;

import lombok.Data;

import java.util.List;

/**
 * 量化指标数据趋势图查询参数
 * @author xiaolv
 *
 */
@Data
public class TrendDataVO {
    /**
     * 信息资源id
     */
    private String infoResourceId;
    /**
     * 趋势类型(0:合计值；1：平均值)
     */
    private int tendencyType;
    /**
     * 组织机构id
     */
    private List<String> organizationList;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
}
