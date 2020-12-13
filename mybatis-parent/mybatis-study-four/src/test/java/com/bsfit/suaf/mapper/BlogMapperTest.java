package com.bsfit.suaf.mapper;

import com.bsfit.suaf.pojo.Blog;
import com.bsfit.suaf.utils.MySqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.*;

public class BlogMapperTest {
    public static final Logger logger = Logger.getLogger(BlogMapperTest.class);

    @Test
    public void addBlog(){
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        String substring = UUID.randomUUID().toString().substring(4, 16).replaceAll("-","");
        mapper.initBlog(new Blog(substring,"wuwuwu","xfgui",new Date(),56));
        sqlSession.close();
    }
    @Test
    public void queryBlog(){
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap<String,Object> map =new HashMap();
        map.put("title","wuwuwu");
        map.put("author","zhangsan");
        List<Blog> blogIfId = mapper.getBlogIfId(map);
        blogIfId.forEach(blog -> logger.info(blog));
        sqlSession.close();
    }
    @Test
    public void queryBlogChoose(){
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap<String,Object> map =new HashMap();
        map.put("views",100);
        map.put("title","wuwuwu");
//        map.put("author","zhangsan");
        List<Blog> blogIfId = mapper.getBlogChoose(map);
        blogIfId.forEach(blog -> logger.info(blog));
        sqlSession.close();
    }
    @Test
    public void testSet(){
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap<String,Object> map =new HashMap();
//        map.put("views",100);
        map.put("title","yyyy");
//        map.put("author","fghj");
        map.put("id","9092-9");
        mapper.getBlogSet(map);
        sqlSession.close();
    }
    @Test
    public void testFore(){
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap<String,Object> map =new HashMap();
        List<String> list = new ArrayList(){{
            add("9092-9");
            add("ebba3bbb46");
            add("ce3103c84d");
        }};
        map.put("ids",list);
        List<Blog> blogFore = mapper.getBlogFore(map);
        blogFore.forEach(blog ->logger.info(blog));
        sqlSession.close();
    }
}
