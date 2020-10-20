package test.basetest;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author bangsun
 *
 * 在需要精确的小数计算时再使用BigDecimal，BigDecimal的性能比double和float差，在处理庞大，复杂的运算时尤为明显。
 * 故一般精度的计算没必要使用BigDecimal。尽量使用参数类型为String的构造函数。
 * BigDecimal都是不可变的（immutable）的， 在进行每一次四则运算时，都会产生一个新的对象 ，所以在做加减乘除运算时要记得要保存操作后的值。
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        /*构造方法*/
        BigDecimal bigDecimal1 = new BigDecimal(1);
        BigDecimal bigDecimal2 = new BigDecimal(10L);
        BigDecimal bigDecimal3 = new BigDecimal("100.0");
        BigDecimal bigDecimal4 = new BigDecimal(1.00d);
        System.out.println("指定int类型构造" + bigDecimal1);
        System.out.println("指定Long类型构造" + bigDecimal2);
        System.out.println("指定String类型构造" + bigDecimal3);
        System.out.println("指定double类型的构造" + bigDecimal4);

        /*
         *参数类型为double的构造方法的结果有一定的不可预知性。
         * 有人可能认为在Java中写入newBigDecimal(0.1)所创建的BigDecimal正好等于 0.1（非标度值 1，其标度为 1），
         * 但是它实际上等于0.1000000000000000055511151231257827021181583404541015625。
         * 这是因为0.1无法准确地表示为 double（或者说对于该情况，不能表示为任何有限长度的二进制小数）。
         * 这样，传入到构造方法的值不会正好等于 0.1（虽然表面上等于该值）。
         * String 构造方法是完全可预知的：写入 newBigDecimal(“0.1”) 将创建一个 BigDecimal，它正好等于预期的 0.1。
         * 因此，比较而言， 通常建议优先使用String构造方法。
         * 当double必须用作BigDecimal的源时，请注意，此构造方法提供了一个准确转换；
         * 它不提供与以下操作相同的结果：先使用Double.toString(double)方法，然后使用BigDecimal(String)构造方法，
         * 将double转换为String。要获取该结果，请使用static valueOf(double)方法。
         * */
        BigDecimal bigDecimal5 = new BigDecimal(0.1);
        System.out.println("double类型的0.1----->" + bigDecimal5);
        BigDecimal bigDecimal6 = new BigDecimal("0.1");
        System.out.println("string类型的0.1----->" + bigDecimal6);
        //小数点后55位和1位
        System.out.println("输出bigDecimal5的位数："+bigDecimal5.scale());
        System.out.println("输出bigDecimal6的位数："+bigDecimal6.scale());
        Double aDouble = Double.valueOf(0.1);
        System.out.println(aDouble);
        System.out.println(new BigDecimal(aDouble));

        /*bigdecimal常用方法 加法*/
        BigDecimal add = bigDecimal1.add(bigDecimal2);
        System.out.println("bigDecimal1+bigDecimal1=" + add);
        /*bigdecimal常用方法 减法*/
        BigDecimal subtract = bigDecimal1.subtract(bigDecimal2);
        System.out.println("bigDecimal1-bigDecimal1=" + subtract);
        /*bigdecimal常用方法 乘法*/
        BigDecimal multiply = bigDecimal1.multiply(bigDecimal2);
        System.out.println("bigDecimal1*bigDecimal1=" + multiply);
        /*bigdecimal常用方法 除法*/
        BigDecimal divide = bigDecimal1.divide(bigDecimal2);
        System.out.println("bigDecimal1/bigDecimal1=" + divide);
        //类型转换
        System.out.println("to String:" + bigDecimal1.toString());
        System.out.println("to 双精度:" + bigDecimal1.doubleValue());
        System.out.println("to 单精度:" + bigDecimal1.floatValue());
        System.out.println("to long型:" + bigDecimal1.longValue());
        System.out.println("to int型:" + bigDecimal1.intValue());

        /*大小比较
          a = -1,表示bigdemical小于bigdemical2；
          a = 0,表示bigdemical等于bigdemical2；
          a = 1,表示bigdemical大于bigdemical2；*/
        int i = bigDecimal1.compareTo(bigDecimal2);
        System.out.println(i);

        /*formate,格式化*/
        //货币格式化
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        //百分比格式化
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        //百分号后最多三位
        percentInstance.setMaximumIntegerDigits(4);
        BigDecimal money = new BigDecimal("100000.8967");
        BigDecimal inter = new BigDecimal("0.008");
        BigDecimal inters = inter.multiply(money);
        System.out.println("贷款金额:\t" + currencyInstance.format(money));
        System.out.println("利率:\t" + percentInstance.format(inter));
        System.out.println("利息:\t" + currencyInstance.format(inters));

        System.out.println(formatToNumber(new BigDecimal("3.435")));
        System.out.println(formatToNumber(new BigDecimal(0)));
        System.out.println(formatToNumber(new BigDecimal("0.00")));
        System.out.println(formatToNumber(new BigDecimal("0.001")));
        System.out.println(formatToNumber(new BigDecimal("0.006")));
        System.out.println(formatToNumber(new BigDecimal("0.206")));


    }
    /**
     * @desc 1.0~1之间的BigDecimal小数，格式化后失去前面的0,则前面直接加上0。
     * 2.传入的参数等于0，则直接返回字符串"0.00"
     * 3.大于1的小数，直接格式化返回字符串
     * @param obj传入的小数
     * @return
     */
    public static String formatToNumber(BigDecimal obj) {
        DecimalFormat df = new DecimalFormat("#.00");
        if(obj.compareTo(BigDecimal.ZERO)==0) {
            return "0.00";
        }else if(obj.compareTo(BigDecimal.ZERO)>0&&obj.compareTo(new BigDecimal(1))<0){
            return "0"+df.format(obj).toString();
        }else {
            return df.format(obj).toString();
        }
    }
}
