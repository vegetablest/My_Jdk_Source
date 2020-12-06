package com.bsfit.suaf.mapper;

import com.bsfit.suaf.pojo.Actor;

import java.util.List;
import java.util.Map;

/**
 * @author bangsun
 */
public interface ActorMapper {
    /**
     * getActors
     * */
    List<Actor> getActor();

    /**
     * 根据ID查询结果
     * */
    Actor getAct(Integer id);
    /**
     * 添加Actor
     *
     * @return*/
    void addActor(Actor actor);
    /**
     * 添加Actor简单方法
     * 万能的map
     * @return*/
    void addActorSimple(Map<String,Object> map);

    /**
     * 删除Actor
     * */
    void delActor(Integer id);
    /**
     * 更新
     * */
    void updateActor(Actor actor);
}
