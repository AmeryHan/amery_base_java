package amery.jdk.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ameryhan
 * @date 2019/8/21 13:22
 */
public class AtomicTest implements Runnable {
    private static Integer count = 1;
    private static AtomicInteger atomic = new AtomicInteger();

    @Override
    public void run() {
        while (true) {
            //int count = getCountAtomic();
            int count = getCount();
            System.out.println(count);
            if (count >= 150) {
                break;
            }
        }
    }

    public synchronized Integer getCount() {
        try {
            Thread.sleep(50);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return count++;
    }

    public Integer getCountAtomic() {
        try {
            Thread.sleep(50);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return atomic.incrementAndGet();
    }

    public static void main(String[] args) {
        AtomicTest test0001 = new AtomicTest();
        Thread t1 = new Thread(test0001);
        Thread t2 = new Thread(test0001);
        t1.start();
        t2.start();
    }

}
