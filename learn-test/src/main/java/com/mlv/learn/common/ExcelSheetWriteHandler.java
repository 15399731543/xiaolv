package com.mlv.learn.common;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFSheet;

/**
 * Excel单元格拦截器
 */
public class ExcelSheetWriteHandler implements SheetWriteHandler {

    // 设置100列column
    private static final Integer COLUMN = 100;

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        for(int i = 0; i < COLUMN; ++i) {
            // 设置为文本格式
            SXSSFSheet sxssfSheet = (SXSSFSheet)writeSheetHolder.getSheet();
            CellStyle cellStyle = writeWorkbookHolder.getCachedWorkbook().createCellStyle();
            // 49为文本格式
            cellStyle.setDataFormat((short)49);
            // i为列，一整列设置为文本格式
            sxssfSheet.setDefaultColumnStyle(i, cellStyle);
        }
    }
}