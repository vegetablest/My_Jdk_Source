package com.bsfit.suaf.mapper;

import com.bsfit.suaf.pojo.Actor;
import com.bsfit.suaf.utils.MySqlSessionUtil;
import com.bsfit.suaf.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class ActorMapperTest {


    @Test
    public void getActorsTest() {
        try {
            SqlSession sqlSession = SqlSessionUtils.getSqlSession();
            ActorMapper actorMapper = sqlSession.getMapper(ActorMapper.class);
            List<Actor> actorList = actorMapper.getActor();
            actorList.forEach(actor -> System.out.println(actor));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Test
    public void getActTest() {
        try {
            SqlSession sqlSession = SqlSessionUtils.getSqlSession();
            ActorMapper actorMapper = sqlSession.getMapper(ActorMapper.class);
            Actor actor = actorMapper.getAct(1);
            System.out.println(actor);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * 增删改需要事务
     */
    @Test
    public void addActorTest() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ActorMapper actorMapper = sqlSession.getMapper(ActorMapper.class);
        actorMapper.addActor(new Actor(202, "zhang", "san", new Date()));
        sqlSession.commit();
    }
    @Test
    public void addActorSimpleTest() throws IOException {
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        ActorMapper actorMapper = sqlSession.getMapper(ActorMapper.class);
        Map<String,Object> map = new HashMap<String, Object>(){{
            put("id",201);
            put("nameOne","王");
            put("nameTwo","五");
            put("time",new Date());
        }};
        actorMapper.addActorSimple(map);
        sqlSession.commit();
    }
    @Test
    public void updateActorTest() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ActorMapper actorMapper = sqlSession.getMapper(ActorMapper.class);
        actorMapper.updateActor(new Actor(201, "li", "si", new Date()));
        sqlSession.commit();
    }
    @Test
    public void delActorTest() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ActorMapper actorMapper = sqlSession.getMapper(ActorMapper.class);
        actorMapper.delActor(201);
        sqlSession.commit();
    }

    @Test
    public void addActorsTest() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ActorMapper actorMapper = sqlSession.getMapper(ActorMapper.class);
        List<Actor> actors = new ArrayList<Actor>(){{
            add((new Actor(202, "zhang", "san", new Date())));
            add((new Actor(203, "zhang", "san", new Date())));
            add((new Actor(204, "zhang", "san", new Date())));
        }};
        sqlSession.commit();
    }
}
