package test.basetest;

public class DownPaymentRatio {
    public static void main(String[] args) {
        DownPaymentRatio.getPayMent(100000000000L,0,300000000000L);
        DownPaymentRatio.getPayMent01(89000,30000,1000000);
        int sum = 10;
        Integer num =10;
        System.out.println(num.equals(10));
        Object o = (Object)sum;
        o = "hello";
        System.out.println(o.equals("hello"));
        System.out.println(o.equals(10) && o.equals("10"));
        Object s = "38.0";
        System.out.println(s instanceof String);
        double v = Double.parseDouble((String) s);
        System.out.println(v);
        int a =0;
        for (int i = 0; i < 5; i++) {
            a++;
        }
        System.out.println(a);
    }
    public static double getPayMent01(Object o1, Object o2, Object o3) {
        double item01 = 0.0;
        double item02 = 0.0;
        double item03 = 0.0;
        double item04 = -1d;
        double item05 = 0.3D;
        System.out.println(item05);
        if (o1 instanceof Number && o2 instanceof Number && o3 instanceof Number) {
            item01 = ((Number) o1).doubleValue();
            item02 = ((Number) o2).doubleValue();
            item03 = ((Number) o3).doubleValue();
            if ((int) item03 == 0) {
                return item04;
            }
            item04=(item01+item02)/item03;
            item04 = (double) Math.round(item04 * 100) / 100;
            System.out.println(item04);
            return item04;
        } else {
            return item04;
        }
    }
    public static double getPayMent(Object o1,Object o2,Object o3){
        double item01=0.0;
        double item02=0.0;
        double item03=0.0;
        double item04=-1d;
        if(o1 instanceof Number && o2 instanceof Number && o3 instanceof Number){
            item01=((Number) o1).longValue();
            item02=((Number) o2).longValue();
            item03=((Number) o3).longValue();
            if ((int)item03 == 0){
                System.out.println(item04);
                return item04;
            }
            item04 = (item01+item02)/item03;
            System.out.println((item04*100));
            return item04;
        }else{
            System.out.println(item04);
            return item04;
        }

    }

}
