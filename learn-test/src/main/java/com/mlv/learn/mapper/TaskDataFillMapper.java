package com.mlv.learn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mlv.learn.dto.DataFillDTO;
import com.mlv.learn.pojo.TaskDataFill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (JkztuvTaskDataFill)表数据库访问层
 *
 * @author xiaolv
 * @since 2024-04-16 21:04:06
 */
public interface TaskDataFillMapper extends BaseMapper<TaskDataFill> {

    List<DataFillDTO> getTaskDataFill(@Param("sql") String sql);
}

