package com.mlv.learn.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mlv.learn.common.TotalAndAverageModel;
import com.mlv.learn.dto.ContentDTO;
import com.mlv.learn.dto.DataFillDTO;
import com.mlv.learn.dto.FillContentDTO;
import com.mlv.learn.mapper.AuthOrganizationMapper;
import com.mlv.learn.mapper.TaskDataFillMapper;
import com.mlv.learn.pojo.TaskDataFill;
import com.mlv.learn.service.TaskDataFillService;
import com.mlv.learn.vo.OrganizationVO;
import com.mlv.learn.vo.TrendDataVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * (JkztuvTaskDataFill)表服务实现类
 *
 * @author xiaolv
 * @since 2024-04-16 21:04:06
 */
@Service
public class TaskDataFillServiceImpl extends ServiceImpl<TaskDataFillMapper, TaskDataFill> implements TaskDataFillService {

    @Autowired
    private AuthOrganizationMapper authOrganizationMapper;
    /**
     * 量化指标数据趋势图
     * TODO json 转 list
     * @param query infoResourceId 资源id
     * query organizationList 组织ids
     * query startTime 开始时间
     * query endTime 结束时间
     * query tendencyType 趋势类型(合计值/平均值)
     * @return List<Map<String,Object>>
     */
    @Override
    public ConcurrentHashMap<String, Object>  getTrendData(TrendDataVO query) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<DataFillDTO> fills = new ArrayList<>();//baseMapper.getTaskDataFill(getSql(query));
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        List<String> xAxis = new ArrayList<>();
        List<String> legend = getLegend(fills,query.getOrganizationList());
        List<String> series = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        List<ContentDTO> contents = new ArrayList<>();
        Map<String, List<ContentDTO>> collect = new HashMap<>();
        if(!fills.isEmpty()){
            for (DataFillDTO fill : fills) {
                //string 转 json
                List<FillContentDTO> jsons = JSON.parseArray(fill.getFillContent(), FillContentDTO.class);
                for (FillContentDTO json : jsons){
                    if(isNumber(json.getContent())){
                        ContentDTO content = new ContentDTO();
                        content.setOrgId(fill.getOrgId());
                        String time = sdf.format(fill.getDateTime());
                        String date = time.substring(0,7).split("-")[0]+"-"+Integer.parseInt(time.substring(0,7).split("-")[1]);
                        content.setDateTime(date);
                        content.setContent(json.getContent());
                        contents.add(content);
                    }
                }
            }
            collect = contents.stream().collect(Collectors.groupingBy(ContentDTO::getDateTime));
            collect.forEach((k, v) -> {
                xAxis.add(k);
            });
        }else {
            xAxis.add(sdf.format(new Date()).substring(0,7).split("-")[0]+"-"+Integer.parseInt(sdf.format(new Date()).substring(0,7).split("-")[1]));
        }
        List<OrganizationVO> orgList = getOrgList(sql,legend);
        for (String s : legend) {
            String name = null;
            if (StringUtils.isNotBlank(s)){
                name = orgList.stream().filter(o-> StringUtils.isNotBlank(s) && o.getId().equals(s)).findFirst().orElse(null).getName();
            }
            if(Objects.nonNull(name)){
                series.add(name);
            }
        }
        series.add("合计值");
        series.add("平均值");
        map.put("legend", series);
        map.put("xAxis", xAxis);
        //计算合计值 和平均值
        List<TotalAndAverageModel> models = new ArrayList<>();
        for (String s : series) {
            TotalAndAverageModel model = new TotalAndAverageModel();
            model.setName(s);
            models.add(model);
            List<Double> r = new ArrayList<>();
            xAxis.forEach(xAxi -> {
                r.add(0.0);
            });
            model.setValue(r);
        }
        map.put("data", models);
        for (int i = 0; i < xAxis.size(); i++) {
            String time = xAxis.get(i);
            double row;
            double total = 0;
            double average = 0;
            List<ContentDTO> data = new ArrayList<>();
            if(!collect.isEmpty()){
                data = collect.get(time);
            }
            TotalAndAverageModel model = models.get(i);
            String name = model.getName();
            String orgId;
            List<ContentDTO> cols = null;
            /*Todo */
            if(orgList.size() > 0){
                orgId = orgList.stream().filter(o->o.getName().equals(name)).findFirst().orElse(null).getId();
                if(!data.isEmpty()){
                    cols = data.stream().filter(e -> e.getDateTime().equals(time) && e.getOrgId().equals(orgId)).collect(Collectors.toList());
                }else {
                    cols = new ArrayList<>();
                }
            }
            if(CollectionUtil.isNotEmpty(cols)){
                row = cols.size();
                for (ContentDTO col : cols) {
                    total += Double.parseDouble(col.getContent());
                }
                average = total / row;
            } else {
                row = 0;
            }
            int index = i;
            //行数
            models.stream().filter(e->e.getName().equals(name)).map(e ->{
                e.getValue().set(index,row);
                return e;
            }).collect(Collectors.toList());
            double finalTotal = total;
            models.stream().filter(e->e.getName().equals("合计值")).map(e ->{
                e.getValue().set(index, finalTotal);
                return e;
            }).collect(Collectors.toList());
            double finalAverage = average;
            models.stream().filter(e->e.getName().equals("平均值")).map(e ->{
                e.getValue().set(index, finalAverage);
                return e;
            }).collect(Collectors.toList());
        }
        return map;
    }
    /**
     * @param sql
     * @param list
     *
     * @return
     */
    //查询企业信息
    private List<OrganizationVO> getOrgList(StringBuffer sql, List<String> list){
        List<OrganizationVO> authOrganizationList = new ArrayList<>();
        if(list.size() > 0){
            sql.append("where id in (");
            String orgStr = list.stream().map(e->"'"+e+"'").collect(Collectors.joining(","));
            sql.append(orgStr).append(")");
            authOrganizationList = authOrganizationMapper.selectOrgById(sql.toString());
        }
        return authOrganizationList.stream().filter(e -> StringUtils.isNotBlank(e.getName())).collect(Collectors.toList());
    }
    private List<String> getLegend(List<DataFillDTO> fills, List<String> organizationList) {
        List<String> legend = new ArrayList<>();
        if(fills.size() > 0 && organizationList.size() == 0){
            legend.addAll(fills.stream().filter(e -> e.getOrgId() != null).map(DataFillDTO::getOrgId).collect(Collectors.toList()));
        }else{
            legend.addAll(organizationList);
        }
        //去重
        Set<String> setWithoutDuplicates = new HashSet<>(legend);
        List<String> result = new ArrayList<>(setWithoutDuplicates);
        return result;
    }
    public boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^[1-9][0-9]*$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    private String getSql(TrendDataVO vo){
        //获取主题下的资源信息
        StringBuffer sql = new StringBuffer("1=1");
        //审批通过
        sql.append(" and operate_status = 5 ");
        if (!vo.getStartTime().equals("")){
            sql.append(" and update_time >= '");
            sql.append(vo.getStartTime()).append("'");
        }
        if(!vo.getEndTime().equals("")){
            sql.append(" and update_time <= '");
            sql.append(vo.getEndTime());
            sql.append("'");
        }
        if(!vo.getInfoResourceId().equals("")){
            sql.append(" and info_resource_id = '");
            sql.append(vo.getInfoResourceId()).append("'");
        }
        if (vo.getOrganizationList().size() > 0){
            sql.append(" and org_id in (");
            String orgStr = vo.getOrganizationList().stream().map(e->"'"+e+"'").collect(Collectors.joining(","));
            sql.append(orgStr);
            sql.append(")");
        }
        sql.append(" order by update_time desc");
        return sql.toString();
    }
}

