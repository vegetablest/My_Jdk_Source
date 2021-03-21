package com.bsfit.suaf.pojo;

import cn.afterturn.easypoi.excel.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

/**
 * <br>
 *
 * @author bangsun
 * @ExcelTarget("user") 导出的目标，属性user是个唯一标识
 * @Excel(name ="姓名")  导出的列名
 * @data 2021/3/21 17:37
 * @verson
 */
@Data
@Accessors(chain = true)
@ExcelTarget("users")
public class User implements Serializable {
    @Excel(name = "编号", orderNum = "0")
    private String id;
    @Excel(name = "姓名", orderNum = "1")
    private String name;
    @Excel(name = "年龄", suffix = "&")
    private Integer age;
    @Excel(name = "生日", orderNum = "3", width = 15, exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date bir;
    @Excel(name = "状态", orderNum = "4", replace = {"激活_1", "锁定_0"})
    private String status;
    @ExcelIgnore
    private String ignore;
    /**
     * 因为是通过get方法设置的，所以可以这样
     */
//    @Excel(name = "爱好",width = 50,orderNum = "5")
    @ExcelIgnore
    private List<String> habbys;
    @Excel(name = "爱好", width = 15, orderNum = "5")
    private String habbyStr;
    /**重写get方法*/
    public String getHabbyStr() {
        StringJoiner stringJoiner = new StringJoiner("、", "", "");
        habbys.forEach(e -> stringJoiner.add(e));
        return stringJoiner.toString();
    }
    /**标识一一对应*/
    @ExcelCollection(name = "订单列表",orderNum = "8")
    private List<Order> orders;
    /**标识一对多*/
    @ExcelEntity
    private Card card;
    /**图片导出type = 2是图片,imageType = 1是图片，2是字节数组*/
    @Excel(name = "头像信息",type = 2, width = 30,height = 60,orderNum = "9",imageType = 1)
    private String photo;
}
