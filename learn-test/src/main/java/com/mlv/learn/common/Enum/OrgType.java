package com.mlv.learn.common.Enum;

/**
 *  组织类型 0: 公司 1: 部门 2: 维度
 * @author xiaolv
 */
public enum OrgType {
    COMPANY(0, "公司"),
    DEPARTMENT(1, "部门"),
    DIMENSION(2, "维度");

    public Integer type;
    public String desc;

    OrgType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

}
