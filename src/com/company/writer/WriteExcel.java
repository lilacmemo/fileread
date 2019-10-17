package com.company.writer;

import com.company.entity.IDCount;
import com.company.entity.SubCount;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/*
*将统计项id热度list与统计项名称热度list按照某种映射
* 关系写入excel当中
* */
public class WriteExcel {
    public void writer(List<IDCount> idCounts, List<SubCount> subCounts){
        // 1.创建一个workbook对应的excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 2.在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet("总体统计热度");
        // 3.在sheet表中添加表头第0行
        HSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(30);
        //定义字体
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        HSSFFont hssfFont = workbook.createFont();
        hssfFont.setFontHeightInPoints((short) 14);
        hssfFont.setColor(HSSFColor.ORANGE.index);
        hssfFont.setFontHeight((short) 20);
        cellStyle.setFont(hssfFont);
        // 4.创建单元格 设置报表头
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("统计项id");
        cell.setCellStyle(cellStyle);
        cell = row.createCell(1);
        cell.setCellValue("数据分类名称");
        cell.setCellStyle(cellStyle);
        cell = row.createCell(2);
        cell.setCellValue("点击次数");
        cell.setCellStyle(cellStyle);

        //设置每行的宽度
        int[] width = {20*256,30*256,10*256};
        for(int i = 0;i < sheet.getLeftCol();i++){
            sheet.setColumnWidth(i,width[i]);
        }

        // 5.写入数据
        int i = 1;
        for (IDCount idCount : idCounts){
            HSSFRow newRow = sheet.createRow(i);
            newRow.setHeightInPoints(20);
            hssfFont.setColor(HSSFColor.RED.index);
            hssfFont.setFontHeightInPoints((short) 12);
            cellStyle.setFont(hssfFont);
            HSSFCell newCell = newRow.createCell(0);
            newCell.setCellValue(idCount.getStaId());
            newCell.setCellStyle(cellStyle);
            newCell = newRow.createCell(1);
            newCell.setCellValue("");
            newCell.setCellStyle(cellStyle);
            newCell = newRow.createCell(2);
            newCell.setCellValue(idCount.getCount());
            newCell.setCellStyle(cellStyle);
            i++;
            for (SubCount subCount : subCounts){
                if (subCount.getStaId().equals(idCount.getStaId())){
                    HSSFRow subRow = sheet.createRow(i);
                    subRow.setHeightInPoints(20);

                    subRow.createCell(0).setCellValue("");
                    subRow.createCell(1).setCellValue(subCount.getSubName());
                    subRow.createCell(2).setCellValue(subCount.getCount());
                    i++;
                }
            }
        }

        try {
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/" +new Date().toString() + ".xls");
            workbook.write(fos);
            System.out.println("写入成功");
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
