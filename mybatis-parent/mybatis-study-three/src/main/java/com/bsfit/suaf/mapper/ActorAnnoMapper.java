package com.bsfit.suaf.mapper;

import com.bsfit.suaf.pojo.Actor;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author bangsun
 * 只是用注解要去mybatis-config.xml中进行接口的绑定
 */
public interface ActorAnnoMapper {

    @Select("select * from actor;")
    List<Actor> getActors();

    @Select("select * from actor where actor_id = #{id}")
    Actor getActorById(Integer id);
}
