package JMHTest;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime) // 测试完成时间
//BenchmarkMode，使用模式，默认是Mode.Throughput，表示吞吐量,
// 其他参数还有AverageTime，表示每次执行时间，SampleTime表示采样时间，
// SingleShotTime表示只运行一次，用于测试冷启动消耗时间，All表示统计前面的所有指标
@OutputTimeUnit(TimeUnit.NANOSECONDS)
//OutputTimeUnit 统计结果的时间单元，这个例子TimeUnit.SECONDS，我们在运行后会看到输出结果是统计每秒的吞吐量
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS) // 预热次数和时间
//Warmup 配置预热次数，默认是每次运行1秒，运行2次，我们的例子是运行2次
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS) // 测试次数和时间
//Measurement 配置执行次数，本例是一次运行5秒，总共运行5次。
// 在性能对比时候，采用默认1秒即可，如果我们用jvisualvm做性能监控，我们可以指定一个较长时间运行。
@Fork(1) // fork 1 个线程
//代表启动多个单独的进程分别测试每个方法，我们这里指定为每个方法启动一个进程。
@State(Scope.Thread)
//当使用@Setup参数的时候，必须在类上加这个参数，不然会提示无法运行。
public class ArrayListAndLinkedListTest {
    private static final int maxSize = 1000; // 测试循环次数
    private static final int operationSize = 100; // 操作次数


    private static ArrayList<Integer> arrayList;
    private static LinkedList<Integer> linkedList;

    public static void main(String[] args) throws RunnerException {
        // 启动基准测试
        Options opt = new OptionsBuilder()
                .include(ArrayListAndLinkedListTest.class.getSimpleName()) // 要导入的测试类
                .build();
        //JMH提供了Runner类能运行Benchmark类，JMH将会被启动，默认情况下运行MyBenchmark类里的所有被@Benchmark标注方法
        new Runner(opt).run(); // 执行测试
    }

    @Setup
    //@Setup 和 @TearDown 是一对注解，作用于方法上，前者用于测试前的初始化工作，
    // 后者用于回收某些资源，比如压测前需要准备一些数据
    public void init() {
        // 启动执行事件
        arrayList = new ArrayList<Integer>();
        linkedList = new LinkedList<Integer>();
        for (int i = 0; i < maxSize; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
    }
    //测试的方法
    @Benchmark
    public void addArrayByFirst(Blackhole blackhole) {
        for (int i = 0; i < +operationSize; i++) {
            arrayList.add(i, i);
        }
        // 为了避免 JIT 忽略未被使用的结果计算
        blackhole.consume(arrayList);
    }

    @Benchmark
    public void addLinkedByFirst(Blackhole blackhole) {
        for (int i = 0; i < +operationSize; i++) {
            linkedList.add(i, i);
        }
        // 为了避免 JIT 忽略未被使用的结果计算
        blackhole.consume(linkedList);
    }
}
/**
 * 输出结果
 * Benchmark                                            Mode  Cnt        Score         Error  Units
 * JMHTest.ArrayListAndLinkedListTest.addArrayByFirst   avgt    5  5583404.181 ± 6787037.940  ns/op
 * JMHTest.ArrayListAndLinkedListTest.addLinkedByFirst  avgt    5   105337.058 ±  274435.902  ns/op
 */
/**
 * 输出信息
 * "C:\Program Files\Java\jdk1.8.0_212\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2019.3.5\lib\idea_rt.jar=10953:C:\Program Files\JetBrains\IntelliJ IDEA 2019.3.5\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_212\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_212\jre\lib\rt.jar;D:\bangsun\gitspace\My_Jdk_Source\JMHTest\target\classes;D:\Apache\apache-maven-3.6.3\repository\org\openjdk\jmh\jmh-core\1.23\jmh-core-1.23.jar;D:\Apache\apache-maven-3.6.3\repository\net\sf\jopt-simple\jopt-simple\4.6\jopt-simple-4.6.jar;D:\Apache\apache-maven-3.6.3\repository\org\apache\commons\commons-math3\3.2\commons-math3-3.2.jar;D:\Apache\apache-maven-3.6.3\repository\org\openjdk\jmh\jmh-generator-annprocess\1.23\jmh-generator-annprocess-1.23.jar" JMHTest.ArrayListAndLinkedListTest
 * # JMH version: 1.23
 * # VM version: JDK 1.8.0_212, Java HotSpot(TM) 64-Bit Server VM, 25.212-b10
 * # VM invoker: C:\Program Files\Java\jdk1.8.0_212\jre\bin\java.exe
 * # VM options: -javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2019.3.5\lib\idea_rt.jar=10953:C:\Program Files\JetBrains\IntelliJ IDEA 2019.3.5\bin -Dfile.encoding=UTF-8
 * # Warmup: 2 iterations, 1 s each
 * # Measurement: 5 iterations, 5 s each
 * # Timeout: 10 min per iteration
 * # Threads: 1 thread, will synchronize iterations
 * # Benchmark mode: Average time, time/op
 * # Benchmark: JMHTest.ArrayListAndLinkedListTest.addArrayByFirst
 * fork一个进程，预热两次，执行五次，第一个Benchmark方法
 * # Run progress: 0.00% complete, ETA 00:00:54
 * # Fork: 1 of 1
 * # Warmup Iteration   1: 790705.683 ns/op
 * # Warmup Iteration   2: 1921504.031 ns/op
 * Iteration   1: 3005749.910 ns/op
 * Iteration   2: 4542606.903 ns/op
 * Iteration   3: 6691064.439 ns/op
 * Iteration   4: 6438715.573 ns/op
 * Iteration   5: 7238884.081 ns/op
 *
 * 输出结果，最大、平均、最小时间
 * Result "JMHTest.ArrayListAndLinkedListTest.addArrayByFirst":
 *   5583404.181 ±(99.9%) 6787037.940 ns/op [Average]
 *   (min, avg, max) = (3005749.910, 5583404.181, 7238884.081), stdev = 1762572.200
 *   CI (99.9%): [≈ 0, 12370442.121] (assumes normal distribution)
 *
 * 第二次执行
 * # JMH version: 1.23
 * # VM version: JDK 1.8.0_212, Java HotSpot(TM) 64-Bit Server VM, 25.212-b10
 * # VM invoker: C:\Program Files\Java\jdk1.8.0_212\jre\bin\java.exe
 * # VM options: -javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2019.3.5\lib\idea_rt.jar=10953:C:\Program Files\JetBrains\IntelliJ IDEA 2019.3.5\bin -Dfile.encoding=UTF-8
 * # Warmup: 2 iterations, 1 s each
 * # Measurement: 5 iterations, 5 s each
 * # Timeout: 10 min per iteration
 * # Threads: 1 thread, will synchronize iterations
 * # Benchmark mode: Average time, time/op
 * # Benchmark: JMHTest.ArrayListAndLinkedListTest.addLinkedByFirst
 * fork一个进程，预热两次，执行五次，第二个Benchmark方法
 * # Run progress: 50.00% complete, ETA 00:00:27
 * # Fork: 1 of 1
 * # Warmup Iteration   1: 84469.056 ns/op
 * # Warmup Iteration   2: 86478.688 ns/op
 * Iteration   1: 118516.575 ns/op
 * Iteration   2: 16422.488 ns/op
 * Iteration   3: 213478.004 ns/op
 * Iteration   4: 86077.036 ns/op
 * Iteration   5: 92191.189 ns/op
 *
 *
 * Result "JMHTest.ArrayListAndLinkedListTest.addLinkedByFirst":
 *   105337.058 ±(99.9%) 274435.902 ns/op [Average]
 *   (min, avg, max) = (16422.488, 105337.058, 213478.004), stdev = 71270.132
 *   CI (99.9%): [≈ 0, 379772.960] (assumes normal distribution)
 *
 *
 * # Run complete. Total time: 00:02:04
 *
 * REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
 * why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
 * experiments, perform baseline and negative tests that provide experimental control, make sure
 * the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
 * Do not assume the numbers tell you what you want them to tell.
 *
 * Benchmark                                            Mode  Cnt        Score         Error  Units
 * JMHTest.ArrayListAndLinkedListTest.addArrayByFirst   avgt    5  5583404.181 ± 6787037.940  ns/op
 * JMHTest.ArrayListAndLinkedListTest.addLinkedByFirst  avgt    5   105337.058 ±  274435.902  ns/op
 *
 * Process finished with exit code 0
 * */