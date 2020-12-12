package com.bsfit.suaf.mapper;

import com.bsfit.suaf.pojo.Blog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

@SuppressWarnings("all")
public interface BlogMapper {

    @Insert("insert into blog() values(#{id},#{title},#{author},#{createTime},#{views})")
    int initBlog(Blog blog);

}
