package com.bsfit.suaf.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelRead {
    private static String path = "D:\\bangsun\\gitspace\\mybatis-parent\\";
    @Test
    public void testRead03() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(path+"easyExcelStudysuaf03.xls");
        /**得到工作薄*/
        Workbook workbook = new HSSFWorkbook(fileInputStream);
        /**获得第一个sheet页*/
        Sheet sheet = workbook.getSheetAt(0);
        /**得到行*/
        Row row1 = sheet.getRow(0);
        /**得到列*/
        Cell cell11 = row1.getCell(0);
        System.out.println(cell11.getStringCellValue());
        fileInputStream.close();

    }
    @Test
    public void testRead07() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(path+"easyExcelStudysuaf07.xlsx");
        /**得到工作薄*/
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        /**获得第一个sheet页*/
        Sheet sheet = workbook.getSheetAt(0);
        /**得到总的行数*/
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        System.out.println(physicalNumberOfRows);
        /**得到行*/
        Row row1 = sheet.getRow(0);
        /**得到总的列数*/
        int physicalNumberOfCells = row1.getPhysicalNumberOfCells();
        System.out.println(physicalNumberOfCells);
        /**得到列*/
        Cell cell11 = row1.getCell(0);
        int cellType = cell11.getCellType();
        if (cellType == XSSFWorkbook.SHEET_STATE_HIDDEN){
            System.out.println(cell11.getStringCellValue());
        }
        fileInputStream.close();
    }

    /**
     * 读取不同类型的数据
     * */

}
