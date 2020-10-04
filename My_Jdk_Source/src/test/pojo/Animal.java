package test.pojo;

public class Animal {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Animal被回收");
        super.finalize();
    }

}
