package test.juctest;

import java.util.concurrent.*;

/**
 * @author bangsun
 */
@SuppressWarnings("all")
public class JUC18ForkJoinTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyForkJoin myForkJoin = new MyForkJoin(0,100);
        forkJoinPool.submit(myForkJoin);
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myForkJoin);
        Integer integer = forkJoinTask.get();
        System.out.println(integer);
    }


}

class MyForkJoin extends RecursiveTask<Integer> {
    private static final Integer NUM = 10;
    private int begin;
    private int end;
    private int result;

    public MyForkJoin(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - begin <= NUM)) {
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        } else {
            int middle = (end + begin) / 2;
            MyForkJoin myForkJoin1 = new MyForkJoin(begin, middle);
            MyForkJoin myForkJoin2 = new MyForkJoin(middle + 1, end);
            myForkJoin1.fork();
            myForkJoin2.fork();
            result = myForkJoin1.join() + myForkJoin2.join();
        }
        return result;
    }

}