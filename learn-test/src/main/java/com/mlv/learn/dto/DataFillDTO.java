package com.mlv.learn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataFillDTO {

    private String id;
    private String fillContent;
    private String orgId;
    private Date dateTime;
}
