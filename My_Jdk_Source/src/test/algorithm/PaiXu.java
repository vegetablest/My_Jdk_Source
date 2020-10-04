package test.algorithm;

import java.util.concurrent.ForkJoinTask;

public class PaiXu{

    public static void main(String[] args) {
        int[] arr = {9,12,7,2,17,55,23};
        efferArray(arr);
        pringArray(arr);
    }
    //冒泡排序
    public static void efferArray(int a[]){
        int num = 0;
        int sum = 0;
        int tum = 0;
        for (int i = 0; i <a.length; i++) {
            for (int j = 0; j <a.length-i-1 ; j++) {
                sum++;
                if (a[j]>a[j+1]){
                    num = a[j+1];
                    a[j+1] = a[j];
                    a[j] = num;
                    tum++;
                }
            }
        }
        System.out.println("总共循环次数"+sum);
        System.out.println("总共移动次数"+tum);
    }
    //打印数组
    public static void pringArray(int[]a){
        System.out.print("排序之后数组[");
        for (int i=0;i<a.length;i++) {
            if (i==a.length-1){
                System.out.print(a[i]);
            }else {
                System.out.print(a[i]+",");
            }
        }
        System.out.println("]");
    }
}