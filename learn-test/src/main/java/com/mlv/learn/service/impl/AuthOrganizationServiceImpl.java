package com.mlv.learn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mlv.learn.common.Enum.OrgType;
import com.mlv.learn.common.TreeNode;
import com.mlv.learn.mapper.AuthOrganizationMapper;
import com.mlv.learn.pojo.AuthOrganization;
import com.mlv.learn.pojo.AuthOrganizationDimension;
import com.mlv.learn.repertory.AuthOrganizationRepertory;
import com.mlv.learn.service.AuthOrganizationService;
import com.mlv.learn.vo.OrgTreeVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 组织机构表  (AuthOrganization)表服务实现类
 *
 * @author xiaolv
 * @since 2024-04-15 20:21:47
 */
@Service
public class AuthOrganizationServiceImpl extends ServiceImpl<AuthOrganizationMapper, AuthOrganization> implements AuthOrganizationService {

    @Resource
    private AuthOrganizationRepertory authOrganizationRepertory;

    //记录修改的元素
    private Map<String,RemoveTree> removeMap = new HashMap<>();
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class RemoveTree{
        String id;
        boolean flag = false;
    }
    /**
     * 获取所有组织机构
     * @return 组织机构树
     */
    @Override
    public Map<String, Object> getAllOrg() {
        /*创建树型*/
        HashSet<OrgTreeVO> tree = queryOrganization();
        Map<String,Object> map = new HashMap<>();
        map.put("tree",tree);
        return map;
    }

    /**
     * TODO 动态树形结构存在问题
     * 查询所有组织机构
     * @return 组织机构树
     */
    @Override
    public HashSet<OrgTreeVO> queryOrganization() {
        HashSet<OrgTreeVO> tree = new HashSet<>();
        /*查询维度信息*/
        List<AuthOrganizationDimension> dimensions = authOrganizationRepertory.getAllDimension();
        if(BeanUtil.isNotEmpty(dimensions)){
            for (AuthOrganizationDimension dimension : dimensions) {
                OrgTreeVO treeVO = new OrgTreeVO();
                treeVO.setId(dimension.getId());
                treeVO.setDimensionId(dimension.getId());
                treeVO.setLabel(dimension.getName());
                treeVO.setName(dimension.getName());
                treeVO.setType(OrgType.DIMENSION.type);
                /*Todo 暂定维度的parentId (parentId = 002)*/
                treeVO.setParentId("002");
                treeVO.setChildSelect(new HashSet<>());
                tree.add(treeVO);
            }
        }
        List<AuthOrganization> result = getAllAuthOrganization();
        //循环维度信息表
        Set<OrgTreeVO> orgList = getChildren(result);
        tree.stream().forEach(dimension -> {
            orgList.stream().forEach(m -> {
                if(dimension.getId().equals(m.getDimensionId())){
                    dimension.getChildSelect().add(m);
                }
            });
        });
        return tree;
    }

    /**
     * 导出组织信息
     * @return
     */

    @Override
    public List<AuthOrganization> exportAuthOrganization() {
        return getAllAuthOrganization();
    }

    /*
    * 递归获取子集
    * */
    private Set<OrgTreeVO> getChildren(List<AuthOrganization> list){
        Set<OrgTreeVO> treeVOS = new HashSet<>();
        for (AuthOrganization m : list) {
            if(removeMap.containsKey(m.getParentId())){
                continue;
            }
            OrgTreeVO treeVO = new OrgTreeVO();
            treeVO.setId(m.getId());
            treeVO.setDimensionId(m.getDimensionId());
            treeVO.setLabel(m.getName());
            treeVO.setLevel(treeVO.getLevel() + 1);
            treeVO.setName(m.getName());
            treeVO.setType(m.getType());
            treeVO.setParentId(m.getParentId());
            treeVO.setChildSelect(new HashSet<>());
            treeVOS.add(treeVO);
            RemoveTree removeTree = new RemoveTree();
            removeTree.setId(treeVO.getId());
            removeTree.setFlag(false);
            removeMap.put(treeVO.getId(),removeTree);
        }
        //遍历节点
        for (OrgTreeVO treeVO : treeVOS) {
            addChildren(treeVOS,treeVO,list);
        }
        /*Todo 删除上级数据
        * */
        treeVOS.removeIf(next -> removeMap.get(next.getId()).isFlag());
        return treeVOS;
    }
    private void addChildren(Set<OrgTreeVO> treeVOS, OrgTreeVO vo, List<AuthOrganization> result) {
        Set<OrgTreeVO> list =  new HashSet<>();
        List<AuthOrganization> collect = result.stream().filter(m -> m.getParentId().equals(vo.getId())).collect(Collectors.toList());
        if(BeanUtil.isNotEmpty(collect)){
            for (AuthOrganization m : collect) {
                if(removeMap.containsKey(m.getId()) && !removeMap.get(m.getId()).isFlag()){
                    OrgTreeVO treeVO = new OrgTreeVO();
                    treeVO.setId(m.getId());
                    treeVO.setDimensionId(m.getDimensionId());
                    treeVO.setLabel(m.getName());
                    treeVO.setName(m.getName());
                    treeVO.setLevel(vo.getLevel() + 1);
                    treeVO.setType(m.getType());
                    treeVO.setParentId(m.getParentId());
                    treeVO.setChildSelect(new HashSet<>());
                    list.add(treeVO);
                    //修改状态
                    removeMap.put(m.getId(),new RemoveTree(m.getId(),true));
                }
            }
        }
        vo.setChildSelect(list);
        for (OrgTreeVO treeVO : list) {
            addChildren(list, treeVO, result);
        }
    }

    public List<AuthOrganization> getAllAuthOrganization() {
        LambdaQueryWrapper<AuthOrganization> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(AuthOrganization::getId,AuthOrganization::getName,AuthOrganization::getParentId,AuthOrganization::getDimensionId,AuthOrganization::getType,AuthOrganization::getCode);
        /*
         * TODO: 正式环境放开维度
         * */
        queryWrapper.eq(AuthOrganization::getDimensionId,"9f06f5093d314469a7fdebdbc2a44466");
        queryWrapper.orderByDesc(AuthOrganization::getId);
        //查询十条数据
        queryWrapper.last("limit 10");
        List<AuthOrganization> result = baseMapper.selectList(queryWrapper);
        return result;
    }

}

