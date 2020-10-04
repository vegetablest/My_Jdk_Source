package test.newfeatures;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * JDK1.8除了新增了lambda表达式、stream流之外，它还新增了全新的日期时间API。
 * 在JDK1.8之前，Java处理日期、日历和时间的方式一直为社区所诟病，将 java.util.Date设定为可变类型，
 * 以及SimpleDateFormat的非线程安全使其应用非常受限。因此推出了java.time包，该包下的所有类都是不可变类型而且线程安全。
 *
 * 关键类
 * Instant：瞬时时间。
 * LocalDate：本地日期，不包含具体时间, 格式 yyyy-MM-dd。
 * LocalTime：本地时间，不包含日期. 格式 yyyy-MM-dd HH:mm:ss.SSS 。
 * LocalDateTime：组合了日期和时间，但不包含时差和时区信息。
 * ZonedDateTime：最完整的日期时间，包含时区和相对UTC或格林威治的时差。
 *
 * Java 8日期时间API总结:
 * 提供了javax.time.ZoneId 获取时区。
 * 提供了LocalDate和LocalTime类。
 * Java 8 的所有日期和时间API都是不可变类并且线程安全，而现有的Date和Calendar API中的java.util.Date和SimpleDateFormat是非线程安全的。
 * 主包是 java.time,包含了表示日期、时间、时间间隔的一些类。里面有两个子包java.time.format用于格式化， java.time.temporal用于更底层的操作。
 * 时区代表了地球上某个区域内普遍使用的标准时间。每个时区都有一个代号，格式通常由区域/城市构成（Asia/Tokyo），在加上与格林威治或 UTC的时差。例如：东京的时差是+09:00。
 * OffsetDateTime类实际上组合了LocalDateTime类和ZoneOffset类。用来表示包含和格林威治或UTC时差的完整日期（年、月、日）和时间（时、分、秒、纳秒）信息。
 * DateTimeFormatter 类用来格式化和解析时间。与SimpleDateFormat不同，这个类不可变并且线程安全，需要时可以给静态常量赋值。DateTimeFormatter类提供了大量的内置格式化工具，同时也允许你自定义。在转换方面也提供了parse()将字符串解析成日期，如果解析出错会抛出DateTimeParseException。DateTimeFormatter类同时还有format()用来格式化日期，如果出错会抛出DateTimeException异常。
 * 再补充一点，日期格式“MMM d yyyy”和“MMM dd yyyy”有一些微妙的不同，第一个格式可以解析“Jan 2 2014”和“Jan 14 2014”，而第二个在解析“Jan 2 2014”就会抛异常，因为第二个格式里要求日必须是两位的。如果想修正，你必须在日期只有个位数时在前面补零，就是说“Jan 2 2014”应该写成 “Jan 02 2014”。
 * */

public class TestLocalDateTime01 {
    public static void main(String[] args) {
        //简单时间测试
        LocalDate localDate = LocalDate.now();
        System.out.println("当前日期："+localDate);//当前日期：2020-08-17
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("当前时间"+localDateTime);//当前时间2020-08-17T14:50:41.415
        System.out.println("当前年："+localDateTime.getYear());//当前年：2020
        System.out.println("当前月："+localDateTime.getMonthValue());//当前月：8
        System.out.println("当前月："+localDateTime.getMonth());//当前月：AUGUST
        System.out.println("当前日："+localDateTime.getDayOfMonth());//当前日：17
        System.out.println("当前时："+localDateTime.getHour());//当前时：14
        System.out.println("当前分："+localDateTime.getMinute());//当前分：50
        System.out.println("当前秒："+localDateTime.getSecond());//当前秒：41
        //格式化时间: 2020-08-17 14:54:22.527
        System.out.println("格式化时间: "+ localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));

        //时间增减
        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime ldt2 = LocalDateTime.now();
        System.out.println("后5天时间:"+ldt.plusDays(5));
        System.out.println("前5天时间并格式化:"+ldt.minusDays(5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println("前一个月的时间:"+ldt2.minusMonths(1).format(DateTimeFormatter.ofPattern("yyyyMM")));
        System.out.println("后一个月的时间:"+ldt2.plusMonths(1)); //2018-06-16
        System.out.println("指定2099年的当前时间:"+ldt.withYear(2099)); //2099-06-21T15:07:39.506
        //后5天时间:2020-08-22T14:57:05.304
        //前5天时间并格式化:2020-08-12
        //前一个月的时间:202007
        //后一个月的时间:2020-09-17T14:57:05.304
        //指定2099年的当前时间:2099-08-17T14:57:05.304

        //创建指定时间
        LocalDate ld3=LocalDate.of(2017, Month.NOVEMBER, 17);
        LocalDate ld4=LocalDate.of(2018, 02, 11);

        //时间差
        LocalDate ld=LocalDate.parse("2017-11-17");
        LocalDate ld2=LocalDate.parse("2018-01-05");
        Period p=Period.between(ld, ld2);
        System.out.println("相差年: "+p.getYears()+" 相差月 :"+p.getMonths() +" 相差天:"+p.getDays());
        // 相差年: 0 相差月 :1 相差天:19


        //瞬时时间
        Instant inst1 = Instant.now();
        System.out.println("当前时间戳 : " + inst1);
        Instant inst2 = inst1.plus(Duration.ofSeconds(10));
        System.out.println("增加之后的时间 : " + inst2);
        System.out.println("相差毫秒 : " + Duration.between(inst1, inst2).toMillis());
        System.out.println("相毫秒 : " + Duration.between(inst1, inst2).getSeconds());

        //时区时间
        ZoneId zoneId= ZoneId.of("America/New_York");
        ZonedDateTime dateTime=ZonedDateTime.now(zoneId);
        System.out.println("美国纽约此时的时间 : " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        System.out.println("美国纽约此时的时间 和时区: " + dateTime);
        //  美国纽约此时的时间 : 2018-12-19 03:52:22.494
        // 美国纽约此时的时间 和时区: 2018-12-19T03:52:22.494-05:00[America/New_York]


    }
}
