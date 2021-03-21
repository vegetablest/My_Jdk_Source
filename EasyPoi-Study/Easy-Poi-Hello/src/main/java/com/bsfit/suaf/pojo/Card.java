package com.bsfit.suaf.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.naming.Name;
import javax.print.attribute.standard.MediaSize;
import java.io.Serializable;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/21 18:47
 * @verson
 */
@ExcelTarget("card")
@Data
@Accessors(chain = true)
public class Card implements Serializable {
    @Excel(name = "身份证号",width = 10,orderNum = "6")
    private String no;
    @Excel(name = "地址",width = 15,orderNum = "7")
    private String address;
}
