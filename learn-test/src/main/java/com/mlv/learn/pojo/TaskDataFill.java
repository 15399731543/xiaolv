package com.mlv.learn.pojo;

import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * (JkztuvTaskDataFill)实体类
 *
 * @author xiaolv
 * @since 2024-04-16 21:04:06
 */
@Data
@TableName(value = "jkztuv_task_data_fill")
public class TaskDataFill implements Serializable {
    private static final long serialVersionUID = 177848114960604902L;
    /**
     * 主键ID
     */
    private String id;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 创建者ID
     */
    private String creatorId;
    /**
     * 更新者ID
     */
    private String updaterId;
    /**
     * 任务资源分配id
     */
    private String taskResourceAssignId;
    /**
     * 填写的数据
     */
    private String fillContent;
    /**
     * 状态，0：未提交，4：待审核，5：审核通过，6:审核不通过
     */
    private Integer operateStatus;
    /**
     * 组织id
     */
    private String orgId;
    /**
     * 创建人姓名
     */
    private String creatorName;
    /**
     * 行标识
     */
    private Integer rowId;


}

