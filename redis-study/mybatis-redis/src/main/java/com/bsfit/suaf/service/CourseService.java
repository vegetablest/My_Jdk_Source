package com.bsfit.suaf.service;

import com.bsfit.suaf.pojo.Course;

import java.util.List;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/19 20:50
 * @verson
 */
public interface CourseService {
    List<Course> getFindAll();
    Course findById(String id);
    void deleteById(String id);
}
