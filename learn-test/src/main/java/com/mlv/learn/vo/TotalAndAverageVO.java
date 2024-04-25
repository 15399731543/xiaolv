package com.mlv.learn.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 计算合计值和平均值
 * @author xiaolv
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalAndAverageVO {
    //序列化
    private static final long serialVersionUID = 1L;
    /**
     * 组织id
     */
    private String orgId;
    /**
     * 组织名称
     */
    private String name;
    /**
     * 合计值
     */
    private long total;
    /**
     * 平均值（小的那个平均值）
     */
    private double average;

    /**
     * 计数
     */
    private int row;

}
