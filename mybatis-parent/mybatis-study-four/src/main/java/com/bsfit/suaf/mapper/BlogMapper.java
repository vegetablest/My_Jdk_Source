package com.bsfit.suaf.mapper;

import com.bsfit.suaf.pojo.Blog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public interface BlogMapper {

    @Insert("insert into blog() values(#{id},#{title},#{author},#{createTime},#{views})")
    int initBlog(Blog blog);

    /**动态SQL之if*/
    List<Blog> getBlogIfId(Map map);
    /**动态SQL之choose*/
    List<Blog> getBlogChoose(Map map);
    /**动态SQL之set*/
    void getBlogSet(Map map);
    /**动态SQL之foreach*/
    List<Blog> getBlogFore(Map map);

}
