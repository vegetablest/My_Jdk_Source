package com.bsfit.suaf.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/21 20:35
 * @verson
 */
@Data
@ExcelTarget("user")
public class User implements Serializable {
    @Excel(name = "编号")
    private String id;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "生日",format = "yyyy-MM-dd")
    private Date bir;
    @Excel(name = "爱好")
    private String habbys;
    @Excel(name = "头像信息",type = 2,savePath = "D:\\bangsun\\gitspace\\EasyPoi-Study\\easypoi_springboot\\src\\main\\resources\\static\\imgs")
    private String photo;
    @Excel(name = "身份证号")
    private String no;
    @Excel(name = "家庭住址")
    private String address;

}
