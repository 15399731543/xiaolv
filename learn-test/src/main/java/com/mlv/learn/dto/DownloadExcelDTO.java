package com.mlv.learn.dto;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 封装下载Excel模板相关属性
 */
public class DownloadExcelDTO {

    private List<Map<String, Object>> list;

    private Map<String, String> fieldMapDesc; //表头标注

    private String sheetName;

    private String tableName;
    
    /**
     * 父级目录
     */
    private String parentName;

    /**
     * zip 文件名称
     */
    private String zipName;

    /**
     * 附件list
     */
    private List<File> fjList;

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getZipName() {
        return zipName;
    }

    public void setZipName(String zipName) {
        this.zipName = zipName;
    }
    
    public String getParentName() {
        return parentName;
    }
    
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<File> getFjList() {
        return fjList;
    }

    public void setFjList(List<File> fjList) {
        this.fjList = fjList;
    }

    public Map<String, String> getFieldMapDesc() {
        return fieldMapDesc;
    }

    public void setFieldMapDesc(Map<String, String> fieldMapDesc) {
        this.fieldMapDesc = fieldMapDesc;
    }

    @Override
    public String toString() {
        return "DownloadExcelDTO{" +
                "list=" + list +
                ", sheetName='" + sheetName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", parentName='" + parentName + '\'' +
                ", zipName='" + zipName + '\'' +
                ", fjList=" + fjList +
                '}';
    }
}
