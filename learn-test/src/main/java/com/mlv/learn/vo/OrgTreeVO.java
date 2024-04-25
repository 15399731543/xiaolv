package com.mlv.learn.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrgTreeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String dimensionId;

    private String label;
    private String name;

    private Integer type;

    private String parentId;

    private int level;//层级关系
    //默认可选
    private Boolean isDisabled = false;
    private Set<OrgTreeVO> childSelect;

}
