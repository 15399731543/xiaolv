package com.mlv.learn.pojo;

import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * (JkztuvSpecialActionEnterprises)实体类
 *
 * @author xiaolv
 * @since 2024-04-15 21:14:05
 */
@Data
@TableName(value = "jkztuv_special_action_enterprises")
public class SpecialActionEnterprises implements Serializable {
    private static final long serialVersionUID = -46048581953850550L;
    /**
     * 主键
     */
    private String id;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date updateTime;
    /**
     * 创建人
     */
    private String creatorId;
    /**
     * 修改人
     */
    private String updaterId;
    /**
     * 企业id
     */
    private String enterpriseId;
    /**
     * 企业名称
     */
    private String enterpriseName;
    /**
     * 企业编码
     */
    private String enterpriseCode;
    /**
     * 企业分类（1 - 双百企业，2 - 科改企业，3 - 国企改革数智化工程）
     */
    private String enterpriseClass;
    /**
     * 企业类型（1 - 央企， 2 - 地方企业）
     */
    private String enterpriseType;
    /**
     * 一级企业
     */
    private String topEnterpriseId;
    /**
     * 一级企业名称
     */
    private String topEnterpriseName;
    /**
     * 一级企业编码
     */
    private String topEnterpriseCode;
    /**
     * 有效起始时间
     */
    private Date startTime;
    /**
     * 有效结束时间
     */
    private Date endTime;
    /**
     * 备注
     */
    private String remark;


}

