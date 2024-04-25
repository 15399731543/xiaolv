package com.mlv.learn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mlv.learn.pojo.AuthOrganization;
import com.mlv.learn.vo.OrgTreeVO;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 组织机构表  (AuthOrganization)表服务接口
 *
 * @author xiaolv
 * @since 2024-04-15 20:21:46
 */
public interface AuthOrganizationService extends IService<AuthOrganization> {

    /**
     * 获取组织信息树
     */

    Map<String,Object> getAllOrg();


    /*
     * 全部组织机构信息
     * */
    HashSet<OrgTreeVO> queryOrganization();

    // 导出组织信息
    List<AuthOrganization> exportAuthOrganization();
}

