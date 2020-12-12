package com.bsfit.suaf.mapper;

import com.bsfit.suaf.pojo.Student;

import java.util.List;

/**
 * @author bangsun
 */
public interface StudentMapper {

    /**
     * 多对一查询是嵌套查询和连表查询
     * */


    /**查询所有老师信息的学生数据*/
    List<Student> getStudents();
    /**查询所有老师信息的学生数据*/
    List<Student> getStudents2();
}
