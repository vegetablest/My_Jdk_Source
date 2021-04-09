package com.bysj.logistics.manage.mapper;

import com.bysj.logistics.manage.pojo.User2;
import com.bysj.logistics.manage.utils.Condition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface User2Mapper {
    long countByExample(Condition example);

    int deleteByExample(Condition example);

    int deleteByPrimaryKey(Integer id);

    int insert(User2 record);

    int insertSelective(User2 record);

    List<User2> selectByExample(Condition example);

    User2 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User2 record, @Param("example") Condition example);

    int updateByExample(@Param("record") User2 record, @Param("example") Condition example);

    int updateByPrimaryKeySelective(User2 record);

    int updateByPrimaryKey(User2 record);
}