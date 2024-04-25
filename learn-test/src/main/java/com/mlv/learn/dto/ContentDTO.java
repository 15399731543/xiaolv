package com.mlv.learn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 量指化趋势图数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentDTO {
    private String orgId;
    private String dateTime;
    private String content;
}
