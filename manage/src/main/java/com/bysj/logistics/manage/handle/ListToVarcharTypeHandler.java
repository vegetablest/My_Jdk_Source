package com.bysj.logistics.manage.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bysj.logistics.manage.pojo.Children;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * <br>
 * 类型转换器，用于数据库的varchar和Java中List<String>类型的相互转换
 * @author bangsun
 * @data 2021/4/8 22:24
 * @verson
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(List.class)
public class ListToVarcharTypeHandler implements TypeHandler<List<Children>> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, List<Children> children, JdbcType jdbcType) throws SQLException {
        //遍历List类型的入参，转化为为JSONString类型
        String s = JSON.toJSONString(children);
        //使用Statement对象插入数据库
        preparedStatement.setString(i, s);
    }

    @Override
    public List<Children> getResult(ResultSet resultSet, String s) throws SQLException {
        // 获取String类型的结果，jsonArray转化为List<Children>
        String resultString = resultSet.getString(s);
        if (StringUtils.isNotEmpty(resultString)) {
            List<Children> childrens = JSONArray.parseArray(resultString, Children.class);
            return childrens;
        }
        return null;
    }

    @Override
    public List<Children> getResult(ResultSet resultSet, int i) throws SQLException {
        // 获取String类型的结果，jsonArray转化为List<Children>
        String resultString = resultSet.getString(i);
        if (StringUtils.isNotEmpty(resultString)) {
            return JSONArray.parseArray(resultString, Children.class);
        }
        return null;
    }

    @Override
    public List<Children> getResult(CallableStatement callableStatement, int i) throws SQLException {
        // 获取String类型的结果，jsonArray转化为List<Children>
        String resultString = callableStatement.getString(i);
        if (StringUtils.isNotEmpty(resultString)) {
            return JSONArray.parseArray(resultString, Children.class);
        }
        return null;
    }
}
