package test.basetest;

import java.util.Date;

public class TestStringMethod {
    public static void main(String[] args) {
        //保留小数点后四位
        double a = (double)10/(double)3;
        double c = Double.parseDouble(String.format("%.4f",a));
        System.out.println(c);
    }
}
