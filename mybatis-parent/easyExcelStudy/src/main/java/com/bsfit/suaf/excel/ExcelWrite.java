package com.bsfit.suaf.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Excel写测试
 * @author bangsun
 */
public class ExcelWrite {

    String path= "D:\\bangsun\\gitspace\\mybatis-parent\\easyExcelStudy";
    @Test
    /**
     * 03 HSSF最多65535行，一下子加载到内存，一下子写出去
     * */
    public void writTest03() throws IOException {
        /**创建工作薄*/
        Workbook workbook = new HSSFWorkbook();
        /**创建一个sheet页*/
        Sheet sheet = workbook.createSheet();
        /**创建第一行 第一行,第二行*/
        Row row1 = sheet.createRow(0);
        Row row2 = sheet.createRow(1);
        /**创建第一列 第一列*/
        Cell cell11 = row1.createCell(0);
        cell11.setCellValue("今天新增加观众");
        Cell cell21 = row1.createCell(1);
        cell21.setCellValue("统计时间");

        Cell cell12 = row2.createCell(0);
        cell12.setCellValue("suaf");
        Cell cell22 = row2.createCell(1);
        cell22.setCellValue(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));


        /**生产输出流*/
        FileOutputStream fileOutputStream = new FileOutputStream(path+"suaf03.xls");
        /**输出到文件*/
        workbook.write(fileOutputStream);
        /**关闭流*/
        fileOutputStream.close();
        /**输出成功*/
        System.out.println("============");
    }

    @Test
    /**
     * 07 XSSF，一行一行加载，一行一行写
     * */
    public void writTest07() throws IOException {
        /**创建工作薄 07*/
        Workbook workbook = new XSSFWorkbook();
        /**创建一个sheet页*/
        Sheet sheet = workbook.createSheet();
        /**创建第一行 第一行,第二行*/
        Row row1 = sheet.createRow(0);
        Row row2 = sheet.createRow(1);
        /**创建第一列 第一列*/
        Cell cell11 = row1.createCell(0);
        cell11.setCellValue("今天新增加观众");
        Cell cell21 = row1.createCell(1);
        cell21.setCellValue("统计时间");

        Cell cell12 = row2.createCell(0);
        cell12.setCellValue("suaf");
        Cell cell22 = row2.createCell(1);
        cell22.setCellValue(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));

        /**生产输出流  生产xslx*/
        FileOutputStream fileOutputStream = new FileOutputStream(path+"suaf07.xlsx");
        /**输出到文件*/
        workbook.write(fileOutputStream);
        /**关闭流*/
        fileOutputStream.close();
        /**输出成功*/
        System.out.println("============");
    }

    @Test
    /**
     * 07 加速版 SXSSF，流式计算
     * */
    public void writTestSXSSF07() throws IOException {
        /**创建工作薄 07*/
        Workbook workbook = new SXSSFWorkbook();
        /**创建一个sheet页*/
        Sheet sheet = workbook.createSheet();
        /**创建第一行 第一行,第二行*/
        Row row1 = sheet.createRow(0);
        Row row2 = sheet.createRow(1);
        /**创建第一列 第一列*/
        Cell cell11 = row1.createCell(0);
        cell11.setCellValue("今天新增加观众");
        Cell cell21 = row1.createCell(1);
        cell21.setCellValue("统计时间");

        Cell cell12 = row2.createCell(0);
        cell12.setCellValue("suaf");
        Cell cell22 = row2.createCell(1);
        cell22.setCellValue(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));

        /**生产输出流  生产xslx*/
        FileOutputStream fileOutputStream = new FileOutputStream(path+"suaf07加速版.xlsx");
        /**输出到文件*/
        workbook.write(fileOutputStream);
        /**关闭流*/
        fileOutputStream.close();
        /**清楚临时文件*/
        ((SXSSFWorkbook)workbook).dispose();
        /**输出成功*/
        System.out.println("============");
    }

    @Test
    /**
     * 03 效率测试，因为一下子加载到内存，一下子写出去，所以比较快
     *  3373
     * */
    public void writTestXL03() throws IOException {
        Long begin = System.currentTimeMillis();
        /**创建工作薄*/
        Workbook workbook = new HSSFWorkbook();
        /**创建一个sheet页*/
        Sheet sheet = workbook.createSheet();
        /**创建第一行 第一行,第二行*/

        for (int i = 0; i <65535 ; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j <10 ; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue("["+i+","+j+"]");
            }
        }
        /**生产输出流*/
        FileOutputStream fileOutputStream = new FileOutputStream(path+"效率03.xls");
        /**输出到文件*/
        workbook.write(fileOutputStream);
        long endTime = System.currentTimeMillis();
        /**关闭流*/
        fileOutputStream.close();
        /**输出成功*/
        System.out.println("============"+(endTime-begin));
    }

    @Test
    /**
     * 07 效率测试 21917
     * */
    public void writTestXL07() throws IOException {
        Long begin = System.currentTimeMillis();
        /**创建工作薄*/
        Workbook workbook = new XSSFWorkbook();
        /**创建一个sheet页*/
        Sheet sheet = workbook.createSheet();
        /**创建第一行 第一行,第二行*/

        for (int i = 0; i <65535 ; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j <10 ; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue("["+i+","+j+"]");
            }
        }
        /**生产输出流*/
        FileOutputStream fileOutputStream = new FileOutputStream(path+"效率07.xlsx");
        /**输出到文件*/
        workbook.write(fileOutputStream);
        long endTime = System.currentTimeMillis();
        /**关闭流*/
        fileOutputStream.close();
        /**输出成功*/
        System.out.println("============"+(endTime-begin));
    }

    @Test
    /**
     * 07 高效版，效率测试 3438
     * */
    public void writTestXL07SXSSF() throws IOException {
        Long begin = System.currentTimeMillis();
        /**创建工作薄*/
        Workbook workbook = new SXSSFWorkbook();
        /**创建一个sheet页*/
        Sheet sheet = workbook.createSheet();
        /**创建第一行 第一行,第二行*/

        for (int i = 0; i <65535 ; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j <10 ; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue("["+i+","+j+"]");
            }
        }
        /**生产输出流*/
        FileOutputStream fileOutputStream = new FileOutputStream(path+"高效率07.xlsx");
        /**输出到文件*/
        workbook.write(fileOutputStream);
        long endTime = System.currentTimeMillis();
        /**关闭流*/
        fileOutputStream.close();
        /**输出成功*/
        System.out.println("============"+(endTime-begin));
        /**清楚临时文件*/
        ((SXSSFWorkbook)workbook).dispose();
    }

}
