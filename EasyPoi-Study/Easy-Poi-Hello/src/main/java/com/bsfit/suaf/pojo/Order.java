package com.bsfit.suaf.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/21 18:56
 * @verson
 */
@ExcelTarget("order")
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Order implements Serializable {
    @Excel(name = "订单编号",width = 10,orderNum = "8")
    private String no;
    @Excel(name = "订单名称",width = 10,orderNum = "9")
    private String name;

}
