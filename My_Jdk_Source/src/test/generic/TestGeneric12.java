package test.generic;

import java.util.ArrayList;

/**
 * @author bangsun
 */
public class TestGeneric12 {
    public static void main(String[] args) {
        ArrayList<String> arrayList1 = new ArrayList<>();
        ArrayList<Integer> arrayList2 = new ArrayList<>();

        System.out.println(arrayList1.getClass().getSimpleName());
        System.out.println(arrayList2.getClass().getSimpleName());
        /*返回true   虽然看着不一样，其实相同的，泛型擦除*/
        System.out.println(arrayList2.getClass() == arrayList1.getClass());
    }
}
