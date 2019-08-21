package amery.jdk.concurrent;

import java.util.Date;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by ahan on 11/07/2017.
 */
public class CyclicBarrierDemo {

    //http://www.jasongj.com/java/thread_communication/

    public static void main(String[] args) {
        int totalThread = 5;
        CyclicBarrier barrier = new CyclicBarrier(totalThread);

        for (int i = 0; i < totalThread; i++) {
            String threadName = "Thread " + i;
            new Thread(() -> {
                System.out.println(String.format("%s\t%s %s", new Date(), threadName, " is waiting"));
                try {
                    barrier.await();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println(String.format("%s\t%s %s", new Date(), threadName, "ended"));
            }).start();
        }
    }

    /**
     *
     */
}
