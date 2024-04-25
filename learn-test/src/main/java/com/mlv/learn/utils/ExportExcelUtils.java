package com.mlv.learn.utils;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.collect.Maps;
import com.mlv.learn.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** excel导出工具类
 * @Author: cxh
 * @Description:
 * @Date: 2022/9/23 10:18
 */
@Slf4j
public class ExportExcelUtils {
    public static MultipartFile exportExcel(String fileName, Class clazz, List list){
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem fileItem = factory.createItem("textField", "text/plain", true, fileName+".xlsx");
        OutputStream os = null;
        try {
            os = fileItem.getOutputStream();
            ExcelWriter excelWriter = EasyExcel.write(os).build();
            WriteSheet writeSheet = EasyExcel.writerSheet(fileName).build();
            writeSheet.setClazz(clazz);
            // 生成excel
            excelWriter.write(list, writeSheet);
            //关闭流
            excelWriter.finish();
            os.close();
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            return multipartFile;
        } catch (IOException e) {
            log.error("导出Excel文件异常", e);
        }
        return null;
    }

    /**
     * 用poi导出Excel文件的静态方法
     *
     * @param list      数据：只能是List<Map<String, Object>>类型
     * @param sheetName Excel的sheet名字
     * @param fieldMapDesc  批注
     * @throws IOException
     */
    public static HSSFWorkbook exportExcel(List<Map<String, Object>> list, String sheetName, Map<String, String> fieldMapDesc) throws IOException {
        //新建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        /*Excel文件创建完毕*/
        //创建Excel的sheet
        HSSFSheet sheet = workbook.createSheet(sheetName);
        //设置整个excel为文本型
        CellStyle textStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
        //标题字体
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        //加粗
        font.setBold(true);
        //创建单元格
        HSSFCellStyle headStyle = workbook.createCellStyle();
        //设置水平对齐方式
        headStyle.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐方式
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headStyle.setFont(font);
        //从list任意一个Map对象里获取标题（字段名或属性名）放到sheet的第一行上，若第一条记录某字段值没有，则会没有该字段
        //创建sheet的第一行
        HSSFRow first = sheet.createRow(0);
        Map<Integer, HSSFRow> rowMap = Maps.newHashMap();
        Drawing<?> drawingPatriarch = sheet.createDrawingPatriarch();

        boolean isComment = false;
        if(CollUtil.isNotEmpty(fieldMapDesc)){
            isComment = true;
        }
        //遍历获取数据
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            for (String key : map.keySet()) {
                //创建单元格
                Cell cell = first.createCell(i);
                //设置值
                cell.setCellValue(key);
                //设置样式
                cell.setCellStyle(headStyle);
                // 添加批注
                if(isComment && StringUtils.isNotBlank(fieldMapDesc.get(key))) {
                    Comment comment = drawingPatriarch.createCellComment(new HSSFClientAnchor(0, 0, 0, 0,
                            (short) cell.getColumnIndex(), cell.getRowIndex(), (short) (cell.getColumnIndex()+1), cell.getRowIndex()+1));

                    comment.setString(new HSSFRichTextString(fieldMapDesc.get(key)));
                    // 将批注添加到单元格对象中
                    cell.setCellComment(comment);
                }
                Object o = map.get(key);
                if(o instanceof ArrayList) {
                    ArrayList<String> value = (ArrayList) o;
                    for(int j = 1; j < value.size()+1; j++) {
                        HSSFRow row = null;
                        if(rowMap.get(j) == null) {
                            row = sheet.createRow(j);
                            rowMap.put(j, row);
                        } else {
                            row =  rowMap.get(j);
                        }
                        HSSFCell cell1 = row.createCell(i);
                        cell1.setCellValue(value.get(j - 1));
                    }
                }

                // 设置为根据内容自动调整列宽
                sheet.autoSizeColumn(i);
                int colWidth = sheet.getColumnWidth(i);
                if(colWidth<255*256){
                    sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
                } else {
                    sheet.setColumnWidth(i, 6000);
                }
            }
            //默认为文本型
            sheet.setDefaultColumnStyle(i, textStyle);
        }
        return workbook;
    }

    public static Boolean importExcel(InputStream inputStream, Integer sheetNo, AnalysisEventListener analysisEventListener) {
        ExcelReader excelReader = null;
        try {
            ReadSheet readSheet = EasyExcel.readSheet(sheetNo).build();
            excelReader = EasyExcel.read(inputStream, analysisEventListener).build();
            excelReader.read(readSheet);
        } catch (Exception e) {
            log.error("sheet页{}导入异常，原因:{}",sheetNo,e.getMessage());
            e.printStackTrace();
            throw new BusinessException("导入失败！原因：" + e.getMessage(), e);
        } finally {
            excelReader.finish();
        }
        return true;
    }
}
