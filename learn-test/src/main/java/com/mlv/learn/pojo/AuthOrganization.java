package com.mlv.learn.pojo;

import java.util.Date;
import java.io.Serializable;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 组织机构表  (AuthOrganization)实体类
 *
 * @author xiaolv
 * @since 2024-04-15 20:21:46
 */
@Data
@ContentRowHeight(120)
@ExcelIgnoreUnannotated // 忽略未标记的列(没加注解的不导出)
@TableName(value = "auth_organization")
public class AuthOrganization implements Serializable {
    private static final long serialVersionUID = 485251336322690468L;
    /**
     * 主键ID
     */
    @ExcelProperty("主键ID")
    private String id;

    private String tenantId;
    /**
     * 维度ID
     */
    @ExcelProperty("维度ID")
    private String dimensionId;
    /**
     * 编码
     */
    @ExcelProperty("编码")
    private String code;
    /**
     * 编码链
     */
    @ExcelProperty("编码链")
    private String codePath;
    /**
     * 名称
     */
    @ExcelProperty("名称")
    private String name;
    /**
     * 组织类型(0:单位，1:部门)
     */
    @ExcelProperty("组织类型(0:单位，1:部门)")
    private Integer type;
    /**
     * 状态 (0：无效，1：有效)
     */
    @ExcelProperty("状态 (0：无效，1：有效)")
    private String status;

    private Integer level;
    /**
     * 父级ID
     */
    @ExcelProperty("父级ID")
    private String parentId;

    private String parentIdLink;

    private Integer grandId;
    /**
     * 评论
     */
    @ExcelProperty("评论")
    private String remark;
    /**
     * 创建人ID
     */
    @ExcelProperty("创建人ID")
    private String creatorId;
    /**
     * 更新人ID
     */
    @ExcelProperty("更新人ID")
    private String updaterId;
    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date createTime;
    /**
     * 更新时间
     */
    @ExcelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date updateTime;


}

