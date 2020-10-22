package test.basetest;

import test.pojo.Person;

import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

/**
 * @author bangsun
 * <p>
 * Reflection(反射）是Java被视为动态语言的关键，反射机制允许程序在执行期借
 * 助于Reflection API取得任何类的内部信息，并能直接操作任意对象的内部属性及
 * 加载完类之后，在堆内存的方法区中就产生了一个Class类型的对象（一个类只有
 * 一个Class对象），这个对象就包含了完整的类的结构信息。我们可以通过这个对
 * 象看到类的结构。这个对象就像一面镜子，透过这个镜子看到类的结构，所以，
 * 我们形象的称之为:反射方法。
 * Class c= Class.forName(java.lang.String")
 * <p>
 * 但是反射十分影响性能，慎用
 */
public class ReflectionTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //通过反射获得一个类的Class对象,通过getclass,通过类的class属性
        Class<?> aClass = Class.forName("test.pojo.Person");
        Class<?> aClass2 = Class.forName("test.pojo.Person");
        Person ps = new Person();
        //一个类只能创建一个class对象,一个类在内存中只有一个class对象，所有属性被封装在这里边,这是这个类的模板
        System.out.println(aClass + "=====" + aClass2);
        System.out.println(aClass.hashCode() + "=====" + aClass2.hashCode() + "===" + (Person.class).hashCode() + "=========" + ps.getClass().hashCode());

        /**
         *  Class本身也是一个类
         *  Class对象只能由系统建立对象
         *  一个加载的类在JVM中只会有一个Class实例
         *  Class对象
         *  一个Class对象对应的是一个加载到JVM中的一个.class文件
         *  每个类的实例都会记得自己是由哪Class实例所生成
         *  通过Class可以完整地得到一个类中的所有被加载的结构
         *  运行的类，唯有先获得相应的
         *  Class类是Reflection的根源，针对任何你想动态加载、
         *
         *
         *  类的主动引用（一定会发生类的初始化)
         * 当虚拟机启动，初始化main方法所在的类
         * new一个类的对象
         * 调用类的静态成员(除了final常量）和静态方法
         * 使用java.lang.reflect包的方法对类进行反射调用
         * 类的被动引用(不会发生类的初始化)致子类初始化
         * 通过数组定义类引用，不会触发此类的初始化
         * 当初始化一个类，如果其父类没有被初始化，则先会初始化它的父类
         *
         * 类的被动引用不会发生类的初始化
         * 通过数组定义类引用，不会触发此类的初始化
         * 引用常量不会触发此类的初始化（常量在链接阶段就存入调用类的常量池中了)
         * 当访问一个静态域时，只有真正声明这个域的类才会被初始化。如:当通过子类引用父类的静态变量，不会导
         * */
        Person person = new Person("ZHANGSNA", 23);
        System.out.println(Person.class);
        Class<? extends Person> clazz = person.getClass();
        System.out.println(clazz);
        System.out.printf("获得该类的父类:%s", clazz.getSuperclass());
        System.out.println();
        System.out.printf("获得类加载器:%s", clazz.getClassLoader());
        System.out.println();
        System.out.printf("获得类加载器方法二:%s", ClassLoader.getSystemClassLoader());
        System.out.println();
        System.out.printf("获得该类的父类:%s", clazz.getClassLoader().getParent());
        System.out.println();
        System.out.printf("获得该类的根类,但是无法加载出来:%s", clazz.getClassLoader().getParent().getParent());
        System.out.println();
        System.out.printf("获得JVM能加载的类的路径:%s", System.getProperty("java.class.path"));
        System.out.println();
        /**
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\charsets.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\deploy.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\access-bridge-64.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\cldrdata.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\dnsns.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\jaccess.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\jfxrt.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\localedata.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\nashorn.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\sunec.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\sunjce_provider.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\sunmscapi.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\sunpkcs11.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\zipfs.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\javaws.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\jce.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\jfr.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\jfxswt.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\jsse.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\management-agent.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\plugin.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\resources.jar;
         * C:\Program Files\Java\jdk1.8.0_212\jre\lib\rt.jar;
         * D:\bangsun\gitspace\My_Jdk_Source\out\production\My_Jdk_Source;
         * C:\Program Files\Java\jdk1.8.0_212\lib\tools.jar;
         * C:\Program Files\JetBrains\IntelliJ IDEA 2019.3.5\lib\idea_rt.jar
         * */


        //通过反射创造对象clazz.newInstance
        Class aClass1 = person.getClass();
        //无参构造
        Person o = (Person) aClass1.newInstance();
        //有参构造new
        Constructor declaredConstructor = aClass1.getDeclaredConstructor(String.class, int.class);
        Person zhangsna = (Person) declaredConstructor.newInstance("zhangsna", 23);
        System.out.println(zhangsna);


        Class<? extends Person> zhang = zhangsna.getClass();
        //private字段
        Field name = zhang.getDeclaredField("name");
        //设置可见
        name.setAccessible(true);
        name.set(zhangsna,"lisi");
        System.out.println(zhangsna);
        //获得方法private
        Method setName = zhang.getMethod("setName", String.class);
        setName.invoke(zhangsna,"zeal") ;
        System.out.println(zhangsna.getName());

        //获得注解及父类注解
//        zhang.getAnnotatedSuperclass();
//        zhang.getAnnotatedSuperclass();

//        ParameterizedType:表示一种参数化类型,比如Collection<String>
//        GenericArrayType:表示一种元素类型是参数化类型或者类型变量的数组类型
//        TypeVariable:是各种类型变量的公共父接口
//        WildcardType:代表一种通配符类型表达式
        //获取方法
        Method test = zhang.getMethod("test", Map.class,List.class);
        //获取方法的参数类型数组
        Type[] genericParameterTypes = test.getGenericParameterTypes();
        //遍历数组
        for (Type genericParameterType : genericParameterTypes) {
            //输出元素化类型，即参数的类型
            System.out.println(genericParameterType);
            //是否是参数化类型
            if (genericParameterType instanceof  ParameterizedType){
                //获取泛型的集合
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    //输出泛型的；类型
                    System.out.println(actualTypeArgument);
                }
            }
        }

    }
}
