package com.bsfit.suaf.mapper;

import com.bsfit.suaf.pojo.Actor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
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
    Actor getActorById(@Param("id")Integer id);


    @Insert("insert into actor(actor_Id,first_name,last_name,last_update)values (#{actorId},#{firstName},#{lastName},#{lastUpdate});")
    int addActorOne(Actor actor);

    @Insert("insert into actor(actor_Id,first_name,last_name,last_update)values (#{actorId},#{firstName},#{lastName},#{lastUpdate});")
    int addActor(Actor actor);
}
