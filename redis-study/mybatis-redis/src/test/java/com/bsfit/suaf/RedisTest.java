package com.bsfit.suaf;

import com.bsfit.suaf.pojo.Course;
import com.bsfit.suaf.pojo.Score;
import com.bsfit.suaf.service.CourseService;
import com.bsfit.suaf.service.ScoreService;
import org.apache.ibatis.cache.Cache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/19 20:52
 * @verson
 */
@SpringBootTest(classes = MybatisApp.class)
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    private CourseService courseService;

    @Autowired
    private ScoreService scoreService;

    @Test
    public void testFindAll() throws Exception {
        /**
         * 开启缓存，第二次命中缓存，不开启缓存会有两次查找Cache cache;
         * 其实缓存底层就算hashmap,所以我们自定义redisCache的时候就实现这个接口
         * 然后配置那里的cache标签选择type,改成我们自己实现的cache
         * */
        List<Course> findAll = courseService.getFindAll();
        TimeUnit.SECONDS.sleep(2);
        findAll.forEach((course) -> System.out.println(course));
        List<Course> findAll2 = courseService.getFindAll();

    }

    @Test
    public void testFindOne() throws Exception {

        Course course1 = courseService.findById("01");

        Course course2 = courseService.findById("01");

    }

    @Test
    public void testDel() throws Exception {
        /**会走cache的clear方法*/
        courseService.deleteById("01");

    }

    @Test
    public void testFind() throws Exception {
        /**会走cache的clear方法*/
        List<Score> all = scoreService.findAll();
        for (Score score : all) {
            System.out.println(score);
        }

    }

    @Test
    public void testMD5() throws Exception {
        /**把结果转为16进制的字符串*/
        String s = DigestUtils.md5DigestAsHex("-1660246649:678206116:com.bsfit.suaf.mapper.CourseMapper.getFindAll:0:2147483647:select c_id,c_name,t_id from course;:SqlSessionFactoryBean".getBytes());
        System.out.println(s);

    }


}
