package com.bysj.logistics.manage.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.bysj.logistics.manage.pojo.Result;
import io.swagger.annotations.ApiModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * <br>
 * 全局异常类
 * @author bangsun
 * @data 2021/4/4 11:41
 * @verson
 */
@ApiModel("全局异常处理器")
@ControllerAdvice(basePackages="com/bysj/logistics/manage/controller")
public class GlobalExceptionHandler {

    private static final Log log = LogFactory.get();

    /**
     * 统一异常处理@ExceptionHandler,主要用于Exception
     * */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<?> error(HttpServletRequest request, Exception e){
        log.error("异常信息：",e);
        return Result.error("-1", "系统异常");
    }

    @ExceptionHandler(CustomerException.class)
    @ResponseBody
    public Result<?> customError(HttpServletRequest request, CustomerException e){
        return Result.error(e.getCode(), e.getMsg());
    }

}
