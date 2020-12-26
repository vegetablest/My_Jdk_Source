## 装配
   @Autowired//默认按type注入
   @Qualifier("cusInfoService")//一般作为@Autowired()的修饰用
   @Resource(name="cusInfoService")//默认按name注入，可以通过name和type属性进行选择性注入
   spring不但支持自己定义的@Autowired注解，还支持几个由JSR-250规范定义的注解，它们分别是@Resource、@PostConstruct以及@PreDestroy。
   @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按 byName自动注入罢了。@Resource有两个属性是比较重要的，分是name和type，Spring将@Resource注解的name属性解析为bean的名字，而type属性则解析为bean的类型。所以如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略。如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。
   @Resource装配顺序
   1. 如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常
   2. 如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常
   3. 如果指定了type，则从上下文中找到类型匹配的唯一bean进行装配，找不到或者找到多个，都会抛出异常
   4. 如果既没有指定name，又没有指定type，则自动按照byName方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配；
## 约束
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/aop
                           http://www.springframework.org/schema/aop/spring-aop.xsd">
</beans>
```
```java
/**
 * @Component
   @Repository
   @Service
   @Controller
 */
```

```java
//静态代理与动态代理
/**
* 动态代理和静态代理角色一样，都是代理接口
* 动态代理的代理类是动态生成的，不是我们直接写好的!
* 动态代理分为两大类:基于接口的动态代理，基于类的动态代理
* 基于接口---JDK动态代理
* 基于类: cglib
* java字节码实现: javasist
* 
* 动态代理
* InvocationHandler接口，invoke方法获取执行动态代理的程序
* Proxy类，获取动态代理的对象
*/
```