package com.bsfit.suaf.mapper;

import com.bsfit.suaf.pojo.Blog;
import com.bsfit.suaf.utils.MySqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

public class BlogMapperTest {
    public static final Logger logger = Logger.getLogger(BlogMapperTest.class);

    @Test
    public void addBlog(){
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        String substring = UUID.randomUUID().toString().substring(4, 16).replaceAll("-","");
        mapper.initBlog(new Blog(substring,"wdfbn","xfgui",new Date(),56));
    }
}
