package com.bysj.logistics.manage.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@ExcelTarget("user")
public class User implements Serializable {

    @Excel(name = "用户名")
    private String username;

    /**
     * 1普通用户0为管理员2为高管3为职工
     * */
    @Excel(name = "用户身份")
    private Integer groupName;

    @ExcelIgnore
    @Excel(name = "用户密码")
    private String password;
    /**
     * 1男2女
     * */
    @Excel(name = "性别")
    private Integer gender;

    @Excel(name = "真实姓名")
    private String realname;

    @Excel(name = "所在部门")
    private String department;

    @Excel(name = "邮箱")
    private String email;

    @Excel(name = "手机号")
    private String phoneNo;

    @ExcelIgnore
    private String avatar;

    @ExcelIgnore
    private Date createTime;
    @ExcelIgnore
    private Date updateTime;
    @ExcelIgnore
    private Date lastPasswordTime;
    @ExcelIgnore
    private Byte isdelete;



}