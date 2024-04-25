package com.mlv.learn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FillContentDTO {
    private String isRelevancy;
    private String mappingField;
    private String indexId;
    private String content;
}
