package com.bsfit.suaf;

import com.bsfit.suaf.config.MyConfig;
import com.bsfit.suaf.pojo.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.Period;
import java.util.Arrays;

/**
 * <br>
 * 主程序，引导SpringBoot的加载
 *     除了我们以前知道的方法，springboot中@Import也能够往容器里边注册组件
 *     @Conditional  底层给组件注册的时候添加一些条件,也就是条件装配
 *     @ImportResource 在随便一个组件上加上这个，并且把以前的xml文件位置写上去，就会重新加载这些配置文件
 *     @ConfigurationProperties  给容器中的组件配置properties文件的属性，配置绑定
 *         这样才行@Component + @ConfigurationProperties
 *     @EnableConfigurationProperties(Car.class)
 *         等于@Component + @ConfigurationProperties
 *
 * @author bangsun
 * @data 2021/4/25 19:36
 * @verson
 */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        /*其实返回所有组件，组件底层依旧是BeanDefinition,我们遍历打印所有组件名字*/
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        Arrays.asList(beanDefinitionNames).forEach(System.out::println);
        /*获取工厂对象*/
        MyConfig bean = run.getBean(MyConfig.class);

        User user1 = bean.user();
        User user2 = bean.user();
        System.out.println(user1 == user2);

        Object pet = run.getBean("pet");
        System.out.println(user1.getPet() == pet);

        System.out.println("容器中包含pet:"+run.containsBean("pet")+",容器中包含tom:"+run.containsBean("tom")+",容器中包含tom2:"+run.containsBean("tom2"));
    }
}
