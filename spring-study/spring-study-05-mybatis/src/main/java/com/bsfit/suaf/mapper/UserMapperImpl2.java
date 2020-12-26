package com.bsfit.suaf.mapper;

import com.bsfit.suaf.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * 第二种方式，不用注入sqlsessionFactory,直接继承SqlSessionDaoSupport
 * */
public class UserMapperImpl2  extends SqlSessionDaoSupport implements UserMapper {

    @Override
    public List<User> selectUser() {
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
