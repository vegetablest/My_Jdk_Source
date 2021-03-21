package com.bsfit.suaf.test;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.bsfit.suaf.pojo.Emp;
import org.junit.Test;

import java.io.FileInputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/21 17:28
 * @verson
 */
public class TestImport {

    @Test
    public void testImport() throws Exception{
        ImportParams params = new ImportParams();
        /**
         * 标题列占了一行，head列占了2行
         * */
        params.setTitleRows(1);
        params.setHeadRows(2);
        params.setSheetNum(1);
        FileInputStream fis = new FileInputStream("D:\\bangsun\\gitspace\\EasyPoi-Study\\emp.xls");
        List<Emp> emps = ExcelImportUtil.importExcel(fis, Emp.class, params);
        emps.forEach(System.out::println);
    }
}
