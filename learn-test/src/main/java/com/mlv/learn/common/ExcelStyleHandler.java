package com.mlv.learn.common;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.AbstractVerticalCellStyleStrategy;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * Excel自定义样式拦截器
 */
public class ExcelStyleHandler extends AbstractVerticalCellStyleStrategy {

    private static final String FONT_NAME = "等线";
    private static final Integer[] COLUMN_INDEX = new Integer[]{1, 2, 3, 4, 7, 8, 9};


    @Override
    protected WriteCellStyle headCellStyle(Head head) {
        return null;
    }

    @Override
    protected WriteCellStyle contentCellStyle(Head head) {
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 背景白色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 12);
        // 字体样式
        contentWriteFont.setFontName(FONT_NAME);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        return contentWriteCellStyle;
    }

}