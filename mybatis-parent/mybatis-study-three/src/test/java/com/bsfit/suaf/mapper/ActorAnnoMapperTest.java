package com.bsfit.suaf.mapper;

import com.bsfit.suaf.pojo.Actor;
import com.bsfit.suaf.utils.MySqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class ActorAnnoMapperTest {

    private static final Logger logger = Logger.getLogger(ActorAnnoMapperTest.class);
    @Test
    public void getActorsTest(){
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        /*本质就是反射，底层还是动态代理，debug能够查看各种信息*/
        ActorAnnoMapper mapper = sqlSession.getMapper(ActorAnnoMapper.class);
        List<Actor> actors = mapper.getActors();
        actors.forEach(obj-> System.out.println(obj));
        logger.info(actors.size());
        sqlSession.close();
    }
    @Test
    public void getActorsByIdTest(){
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        /*本质就是反射，底层还是动态代理，debug能够查看各种信息*/
        ActorAnnoMapper mapper = sqlSession.getMapper(ActorAnnoMapper.class);
        Actor actors = mapper.getActorById(1);
        logger.info(actors);
        sqlSession.close();
    }
    @Test
    public void addActorTest(){
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        /*本质就是反射，底层还是动态代理，debug能够查看各种信息*/
        ActorAnnoMapper mapper = sqlSession.getMapper(ActorAnnoMapper.class);
        int i = mapper.addActor(new Actor(209, "王五", "赵六", new Date()));
        System.out.println(i);
        sqlSession.close();
    }
    @Test
    public void addActorOne(){
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        ActorAnnoMapper mapper = sqlSession.getMapper(ActorAnnoMapper.class);
        int i = mapper.addActorOne(new Actor(208, "王五", "赵六", new Date()));
        System.out.println(i);
        sqlSession.close();

    }
}
