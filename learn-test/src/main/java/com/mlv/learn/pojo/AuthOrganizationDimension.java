package com.mlv.learn.pojo;

import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

/**
 * 组织机构维度(AuthOrganizationDimension)实体类
 *
 * @author xiaolv
 * @since 2024-04-15 21:09:33
 */
@Data
@TableName(value = "auth_organization_dimension")
public class AuthOrganizationDimension implements Serializable {
    private static final long serialVersionUID = -36098004259531336L;/**
     * ID
     */
    private String id;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 名称
     */
    private String name;
    /**
     * 编码
     */
    private String code;
    /**
     * 描述
     */
    private String description;

    /**
     * 创建人
     */
    private String creatorId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date createTime;

    /**
     * 更新人
     */
    private String updaterId;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date updateTime;


}

