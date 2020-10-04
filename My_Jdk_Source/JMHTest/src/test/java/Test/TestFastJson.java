package Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Before;
import org.junit.Test;
import pojo.Person;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class TestFastJson {

    private List<Person> listOfPersons = new ArrayList<Person>();

    @Before//执行前置
    public void setUp() {
        listOfPersons.add(new Person(15, "John Doe", new Date()));
        listOfPersons.add(new Person(20, "Janette Doe", new Date()));
    }

    @Test//测试
    public void whenJavaList_thanConvertToJsonCorrect() {
        //转化toString()功能
        String jsonOutput = JSON.toJSONString(listOfPersons);
        System.out.println(jsonOutput);
        //BeanToArray序列化功能，serializer=false的字段不会序列化出来
        String jsonOutput1 = JSON.toJSONString(listOfPersons, SerializerFeature.BeanToArray);
        System.out.println(jsonOutput1);
    }

    //通过源码发现JSONArray和JSONObject分别实现了list和map，因此我们能够将JSONObject当做Map<String,Object>把JSONArray当作List<Object>
    //public class JSONArray extends JSON implements List<Object>, Cloneable, RandomAccess, Serializable
    //public class JSONObject extends JSON implements Map<String, Object>, Cloneable, Serializable, InvocationHandler
    //由于 JSONObject 和 JSONArray 继承了 JSON，所以说也可以直接使用两者对 JSON 格式字符串与 JSON 对象及
    // javaBean 之间做转换，不过为了避免混淆我们还是使用 JSON
    @Test
    public void whenGenerateJson_thanGenerationCorrect() throws ParseException {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 2; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("AGE", 10);
            jsonObject.put("FULL NAME", "Doe " + i);
            jsonObject.put("DATE OF BIRTH", "2016/12/12 12:12:12");
            jsonArray.add(jsonObject);
        }
        String jsonOutput = jsonArray.toJSONString();
        System.out.println(jsonOutput);
    }

    @Test
    public void whenJson_thanConvertToObjectCorrect() {
        Person person = new Person(15, "John Doe", new Date());
        //变成时间戳存在string中
        String jsonObject = JSON.toJSONString(person);
        Person newPerson = JSON.parseObject(jsonObject, Person.class);
        System.out.println(person);
        System.out.println(jsonObject);
        System.out.println(newPerson);
    }

    @Test
    public void whenJson_thanConvertToObjectCorrect1() {
        Person person = new Person(20, "John", new Date());
        String jsonObject = JSON.toJSONString(person);
        Person newPerson = JSON.parseObject(jsonObject, Person.class);
        assertEquals(newPerson.getAge(), 0); // 如果我们设置系列化为 false，那么反序列化不出来age默认为0，断言通过
        //断言不通过，应为fullname不一样
        assertEquals(newPerson.getFullName(), listOfPersons.get(0).getFullName());
    }

    //    NameFilter: 序列化时修改 Key。
//    SerializeConfig：内部是个map容器主要功能是配置并记录每种Java类型对应的序列化类。
    @Test
    public void givenSerializeConfig_whenJavaObject_thanJsonCorrect() {
        NameFilter formatName = new NameFilter() {
            public String process(Object object, String name, Object value) {
                return name.toLowerCase().replace(" ", "_");
            }
        };
        SerializeConfig.getGlobalInstance().addFilter(Person.class, formatName);
        String jsonOutput =
                JSON.toJSONStringWithDateFormat(listOfPersons, "yyyy-MM-dd");
        System.out.println(jsonOutput);
    }
}
/*      断言
        void assertEquals(boolean expected, boolean actual):检查两个变量或者等式是否平衡
        void assertTrue(boolean expected, boolean actual):检查条件为真
        void assertFalse(boolean condition):检查条件为假
        void assertNotNull(Object object):检查对象不为空
        void assertNull(Object object):检查对象为空
        void assertSame(boolean condition):assertSame() 方法检查两个相关对象是否指向同一个对象
        void assertNotSame(boolean condition):assertNotSame() 方法检查两个相关对象是否不指向同一个对象
        void assertArrayEquals(expectedArray, resultArray):assertArrayEquals() 方法检查两个数组是否相等
        */
/*
    注解
*   @Test:这个注释说明依附在 JUnit 的 public void 方法可以作为一个测试案例。
    @Before:有些测试在运行前需要创造几个相似的对象。在 public void 方法加该注释是因为该方法需要在 test 方法前运行。
    @After:如果你将外部资源在 Before 方法中分配，那么你需要在测试运行后释放他们。在 public void 方法加该注释是因为该方法需要在 test 方法后运行。
    @BeforeClass:在 public void 方法加该注释是因为该方法需要在类中所有方法前运行。
    @AfterClass:它将会使方法在所有测试结束后执行。这个可以用来进行清理活动。
    @Ignore:这个注释是用来忽略有关不需要执行的测试的。
* */

 /*
    * Unit 执行测试
    测试用例是使用 JUnitCore 类来执行的。JUnitCore 是运行测试的外观类。要从命令行运行测试，
    * 可以运行java org.junit.runner.JUnitCore。对于只有一次的测试运行，可以使用静态方法 runClasses(Class[])。

    JUnit 套件测试
    测试套件意味着捆绑几个单元测试用例并且一起执行他们。在 JUnit 中，@RunWith和@Suite注释用来运行套件测试。spring boot测试常用
 * */