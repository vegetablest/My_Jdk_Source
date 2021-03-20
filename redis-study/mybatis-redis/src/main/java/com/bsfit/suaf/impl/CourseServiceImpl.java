package com.bsfit.suaf.impl;

import com.bsfit.suaf.mapper.CourseMapper;
import com.bsfit.suaf.pojo.Course;
import com.bsfit.suaf.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/19 20:50
 * @verson
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired(required = false)
    private CourseMapper courseMapper;
    @Override
    public List<Course> getFindAll() {
        return courseMapper.getFindAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Course findById(String id) {
        return courseMapper.findById(id);
    }

    @Override
    public void deleteById(String id) {
        courseMapper.deleteById(id);
    }

}
