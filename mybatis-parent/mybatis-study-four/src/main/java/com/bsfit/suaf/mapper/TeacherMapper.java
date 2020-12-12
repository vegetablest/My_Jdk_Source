package com.bsfit.suaf.mapper;

import com.bsfit.suaf.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author bangsun
 */
public interface TeacherMapper {

//    @Select("select * from teacher where id = #{id}")
//    Teacher getTeacher(@Param("id")Integer id);
    /**
     * 一对多
     * */

    Teacher getTeacher(Integer id);

    /**
     * 获取指定老师下的所有学生信息
     * */
    Teacher getTeacherOne(Integer id);
    /**
     * 获取指定老师下的所有学生信息
     * */
    Teacher getTeacherOne2(Integer id);
}
