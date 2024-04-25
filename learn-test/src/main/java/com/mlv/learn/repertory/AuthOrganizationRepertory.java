package com.mlv.learn.repertory;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mlv.learn.mapper.AuthOrganizationDimensionMapper;
import com.mlv.learn.mapper.AuthOrganizationMapper;
import com.mlv.learn.pojo.AuthOrganization;
import com.mlv.learn.pojo.AuthOrganizationDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthOrganizationRepertory {
    @Autowired
    private AuthOrganizationMapper authOrganizationMapper;
    @Autowired
    private AuthOrganizationDimensionMapper authOrganizationDimensionMapper;
    public void insert(AuthOrganization authOrganization) {
        authOrganizationMapper.insert(authOrganization);
    }
    /**
     * 查询所有组织列表
     */
//    public List<AuthOrganization> getAllAuthOrganization() {
//        LambdaQueryWrapper<AuthOrganization> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.select(AuthOrganization::getId,AuthOrganization::getName,AuthOrganization::getParentId,AuthOrganization::getDimensionId,AuthOrganization::getType,AuthOrganization::getCode);
//        queryWrapper.orderByDesc(AuthOrganization::getId);
//        List<AuthOrganization> result = authOrganizationMapper.selectList(queryWrapper);
//        return result;
//    }
    /*
    * 查询维度信息列表
    * */
    public List<AuthOrganizationDimension> getAllDimension() {
        LambdaQueryWrapper<AuthOrganizationDimension> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(AuthOrganizationDimension::getId,AuthOrganizationDimension::getName,AuthOrganizationDimension::getCode);
        queryWrapper.orderByDesc(AuthOrganizationDimension::getId);
        List<AuthOrganizationDimension> result = authOrganizationDimensionMapper.selectList(queryWrapper);
        return result;
    }

}
