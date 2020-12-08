package com.bsfit.suaf.mapper;

import com.bsfit.suaf.pojo.ActorChange;
import com.bsfit.suaf.utils.MySqlSessionUtil;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ActorChangeMapperTest {
    private static Logger logger = Logger.getLogger(ActorChangeMapperTest.class);
    @Test
    public void getActorChangesTest() {
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        ActorChangeMapper actorChangeMapper = sqlSession.getMapper(ActorChangeMapper.class);
        List<ActorChange> actorChanges = actorChangeMapper.getActorChanges();
        /*输出customerId列为空因为映射不上*/
        actorChanges.forEach(actorChange -> System.out.println(actorChange));
    }
    @Test
    public void getActorChangeTest() {
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        ActorChangeMapper actorChangeMapper = sqlSession.getMapper(ActorChangeMapper.class);
        ActorChange actorChange = actorChangeMapper.getActorChange(1);
        /*输出customerId列为空因为映射不上，现在可以使用SQL起别名或者手动映射*/
        System.out.println(actorChange);
    }
    @Test
    public void getActorChanTest() {
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        ActorChangeMapper actorChangeMapper = sqlSession.getMapper(ActorChangeMapper.class);
        ActorChange actorChange = actorChangeMapper.getActorChan(1);
        /*输出customerId列为空因为映射不上，resultMap映射*/
        System.out.println(actorChange);
    }
    @Test
    public void getActorByLimitTest() {
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        ActorChangeMapper actorChangeMapper = sqlSession.getMapper(ActorChangeMapper.class);
        Map<String,Integer> map = new HashMap<String, Integer>(){{
            put("startIndex",0);
            put("endIndex",99);
        }};
        List<ActorChange> actorByLimit = actorChangeMapper.getActorByLimit(map);
        logger.info("actorByLimit查询出来结果:"+actorByLimit.size()+"条");
        /*输出customerId列为空因为映射不上，resultMap映射*/
        List<ActorChange> collect = actorByLimit.stream().filter(obj -> obj.getCustomerId() >= 90).collect(Collectors.toList());
        logger.info(collect.size());
        sqlSession.close();
    }
    @Test
    public void getActorByRowBoudsTest() {
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        List<Object> list = sqlSession.selectList("com.bsfit.suaf.mapper.ActorChangeMapper.getActorByRowBouds");
        for (Object o : list) {
            System.out.println((ActorChange)o);
        }
        sqlSession.close();
    }
    @Test
    public void getActorByRowBoudsTest2() {
        SqlSession sqlSession = MySqlSessionUtil.getSqlSession();
        RowBounds rowBounds = new RowBounds(1, 2);
        List<Object> list = sqlSession.selectList("com.bsfit.suaf.mapper.ActorChangeMapper.getActorByRowBouds",null,rowBounds);
        for (Object o : list) {
            System.out.println((ActorChange)o);
        }
        logger.info(list.size());
        sqlSession.close();
    }

    @Test
    public void log4jTest() {
        logger.info("log4j：日志输出");
        logger.debug("debug：日志输出");
        logger.error("error：日志输出");
    }

}
