package com.bsfit.suaf.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/21 19:40
 * @verson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget("emps")
public class Emp {
    @Excel(name = "编号")
    private String no;
    @Excel(name = "年龄")
    private int age;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "生日",format = "yyyy-MM-dd HH:mm:ss")
    private Date bir;
    @Excel(name = "状态",replace = {"激活_1","锁定_0"})
    private int status;
}
