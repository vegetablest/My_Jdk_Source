package com.bsfit.suaf.mapper;

import com.bsfit.suaf.pojo.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/19 20:35
 * @verson
 */
public interface CourseMapper {
    List<Course> getFindAll();
    Course findById(String id);
    void deleteById(String id);
}
