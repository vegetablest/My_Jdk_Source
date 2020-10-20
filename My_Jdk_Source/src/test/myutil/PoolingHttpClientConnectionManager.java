package test.myutil;

public class PoolingHttpClientConnectionManager {
    public static void main(String[] args) {
        System.out.println("dell 分支");
        double a = (double) 10 /(double) 3;
        System.out.println(a);
        double c=Double.parseDouble(String.format("%.4f",a ));
        a = c;
        System.out.println(a);
        Double.parseDouble(String.format("%.4f",a));
    }
}
