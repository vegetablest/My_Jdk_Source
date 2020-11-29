package test.generic;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author bangsun
 */
public class TestGeneric03<T> {

    public static void main(String[] args) {
        TestGeneric03<String> testGeneric03 = new TestGeneric03<>();
        String[] strProducts = {"奖品1","奖品2","奖品3"};
        /*填充奖品*/
        Arrays.asList(strProducts).forEach(a -> testGeneric03.addProduct(a));
        /*抽奖*/
        String product = testGeneric03.getProduct();
        System.out.printf("抽中奖品%s",product);
    }

    /**
     * 奖品
     * */
    private T product;

    Random random = new Random();

    /**
     * 奖品池
     * */
    ArrayList<T> arrayList = new ArrayList();
    /**
     * 添加奖品
     * */
    public void addProduct(T product){
        arrayList.add(product);
    }
    /**
     * 抽取奖品
     * */
    public T getProduct(){
        product = arrayList.get(random.nextInt(arrayList.size()));
        return product;
    }

}
