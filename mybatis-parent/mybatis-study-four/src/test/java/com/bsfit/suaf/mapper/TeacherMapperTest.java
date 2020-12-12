package com.bsfit.suaf.mapper;

import com.bsfit.suaf.pojo.Student;
import com.bsfit.suaf.pojo.Teacher;
import com.bsfit.suaf.utils.MySqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;


public class TeacherMapperTest {

    public static final Logger logger = Logger.getLogger(TeacherMapperTest.class);

    @Test
    public void getTeacherTest(){
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = mapper.getTeacher(1);
        logger.info(teacher);
        sqlSession.close();
    }

    @Test
    public void getStudent(){
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.getStudents();
        logger.info(students.get(0));
        sqlSession.close();
    }
    @Test
    public void getStudents(){
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.getStudents();
        students.forEach(stu -> System.out.println(stu));
        sqlSession.close();
    }
    @Test
    public void getStudents2(){
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.getStudents2();
        students.forEach(stu -> System.out.println(stu));
        sqlSession.close();
    }

    @Test
    public void getTeacherTests(){
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacherOne = mapper.getTeacherOne(1);
        logger.info(teacherOne);
        sqlSession.close();
    }
    @Test
    public void getTeacherTests2(){
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacherOne = mapper.getTeacherOne2(1);
        logger.info(teacherOne);
        sqlSession.close();
    }
}
