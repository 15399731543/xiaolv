package com.mlv.learn.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalAndAverageModel {
    private String name;
    private List<Double> value;
}
