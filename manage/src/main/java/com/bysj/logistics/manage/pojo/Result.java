package com.bysj.logistics.manage.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <br>
 * 前后端数据交互报文格式
 * {
 *     "code":"0",
 *     "msg":"请求成功",
 *     "data":"[{"":"","":"","":""}]"
 * }
 * @author bangsun
 * @data 2021/4/3 21:08
 * @verson
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "基础返回类", description = "Respond Data Structure")
public class Result<T> implements Serializable {

    @ApiModelProperty(value = "响应状态码",example = "0")
    private String code;
    @ApiModelProperty(value = "提示信息",example = "成功")
    private String msg;
    @ApiModelProperty(value = "数据对象",example = "{'username':'张三','password':'123456'}")
    private T data;

    public static Result succ(Object data) {
        Result m = new Result();
        m.setCode("200");
        m.setMsg("操作成功");
        m.setData(data);
        return m;
    }
    public static Result succ() {
        Result m = new Result();
        m.setCode("200");
        m.setData(null);
        m.setMsg("操作成功");
        return m;
    }

    public static Result succ(String mess, Object data) {
        Result m = new Result();
        m.setCode("200");
        m.setMsg(mess);
        m.setData(data);
        return m;
    }
    public static Result succ(String mess, Object data,String token) {
        Result m = new Result();
        m.setCode("200");
        m.setMsg(mess);
        m.setData(data);
        return m;
    }

    public static Result fail(String mess) {
        Result m = new Result();
        m.setCode("-1");
        m.setData(null);
        m.setMsg(mess);
        return m;
    }

    public static Result fail(String mess, Object data) {
        Result m = new Result();
        m.setCode("-1");
        m.setData(data);
        m.setMsg(mess);
        return m;
    }
    public static Result error(String code,String mess) {
        Result m = new Result();
        m.setCode(code);
        m.setMsg(mess);
        return m;
    }
}