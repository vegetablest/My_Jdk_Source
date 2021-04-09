package com.bysj.logistics.manage.mapper;

import com.bysj.logistics.manage.pojo.User;

/**
 * userMapper
 * @author bangsun
 */
public interface UserMapper {
    /**
     * 根据用户号删除用户
     * */
    int deleteByPrimaryKey(String username);

    /**
     * 添加用户
     * */
    int insert(User record);

    /**
     * 根据用户号删除用户
     * */
    int insertSelective(User record);

    /**
     * 根据用户号查找用户
     * */
    User selectByPrimaryKey(String username);

    /**
     * 根据用户号更新用户
     * */
    int updateByPrimaryKeySelective(User record);

    /**
     * 根据用户号更新用户
     * */
    int updateByPrimaryKey(User record);
}