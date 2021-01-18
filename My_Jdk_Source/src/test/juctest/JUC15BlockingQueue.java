package test.juctest;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author bangsun
 */
@SuppressWarnings("ALL")
public class JUC15BlockingQueue {
    public static void main(String[] args) {

        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(2);
        System.out.println(arrayBlockingQueue.add("a1"));
        System.out.println(arrayBlockingQueue.add("a2"));
        //Exception in thread "main" java.lang.IlleglStateException: Queue full
//        System.out.println(arrayBlockingQueue.add("a3"));
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        //Exception in thread "main" java.util.NoSuchElementException
//        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.offer("a1"));
        System.out.println(arrayBlockingQueue.offer("a2"));
        System.out.println(arrayBlockingQueue.offer("a3"));

        BlockingQueue linkedBlockingDeque = new LinkedBlockingQueue<String>();

        BlockingQueue synchronousQueue = new SynchronousQueue();

        BlockingQueue delayQueue = new DelayQueue();
        BlockingQueue linkedTransferQueue = new LinkedTransferQueue();
        BlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();
    }
}
