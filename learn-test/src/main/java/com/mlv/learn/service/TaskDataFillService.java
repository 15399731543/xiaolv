package com.mlv.learn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mlv.learn.pojo.TaskDataFill;
import com.mlv.learn.vo.TotalAndAverageVO;
import com.mlv.learn.vo.TrendDataVO;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * (JkztuvTaskDataFill)表服务接口
 *
 * @author xiaolv
 * @since 2024-04-16 21:04:06
 */
public interface TaskDataFillService extends IService<TaskDataFill> {

    /**
     * 量化指标数据趋势图
     * @param query infoResourceId 信息资源id
     * query organizationList 组织ids
     * query startTime 开始时间
     * query endTime 结束时间
     * query tendencyType 指标类型
     * query 量化指标数据趋势图数据
     */
    ConcurrentHashMap<String, Object>  getTrendData(TrendDataVO query);
}

