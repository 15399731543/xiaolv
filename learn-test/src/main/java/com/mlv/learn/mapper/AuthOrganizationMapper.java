package com.mlv.learn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mlv.learn.pojo.AuthOrganization;
import com.mlv.learn.vo.OrganizationVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 组织机构表  (AuthOrganization)表数据库访问层
 *
 * @author xiaolv
 * @since 2024-04-15 20:21:43
 */
public interface AuthOrganizationMapper extends BaseMapper<AuthOrganization> {

    //更具组织id查询组织信息
    List<OrganizationVO> selectOrgById(@Param("sql") String sql);
}

