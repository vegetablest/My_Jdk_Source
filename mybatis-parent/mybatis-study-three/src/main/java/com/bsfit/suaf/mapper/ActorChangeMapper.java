package com.bsfit.suaf.mapper;

import com.bsfit.suaf.pojo.ActorChange;

import java.util.List;
import java.util.Map;

/**
 * @author bangsun
 */
public interface ActorChangeMapper {
    /**
     * resultMap用法,不用时映射不上，但是不报错
     * */
    List<ActorChange> getActorChanges();
    /**
     * 起别名映射封装用法
     * */
    ActorChange getActorChange(Integer id);
    /**
     * resultMap用法
     * */
    ActorChange getActorChan(Integer id);

    /**
     * 分页查询limit
     * */
    List<ActorChange> getActorByLimit(Map<String,Integer> map);
    /**
     * 分页查询,通过RowBouds，不建议用
     * */
    List<ActorChange> getActorByRowBouds();
}
