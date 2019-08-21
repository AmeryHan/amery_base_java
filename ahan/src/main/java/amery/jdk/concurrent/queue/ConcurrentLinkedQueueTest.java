package amery.jdk.concurrent.queue;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentLinkedQueueTest {

    private static int count = 2; // 线程个数

    private static CountDownLatch latch = new CountDownLatch(count);
    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();

    public static void main(String[] args) throws InterruptedException {

        long timeStart = System.currentTimeMillis();

        ExecutorService es = Executors.newFixedThreadPool(4);

        ConcurrentLinkedQueueTest.offer();

        for (int i = 0; i < count; i++) {

            es.submit(new Poll());

        }

        latch.await(); //使得主线程(main)阻塞直到latch.countDown()为零才继续执行

        System.out.println("cost time " + (System.currentTimeMillis() - timeStart) + "ms");

        es.shutdown();

    }

    /**
     * 生产
     * add 和offer() 都是加入元素的方法(在ConcurrentLinkedQueue中这俩个方法没有任何区别)
     * poll() 和peek() 都是取头元素节点，区别在于前者会删除元素，后者不会。
     */

    public static void offer() {

        for (int i = 0; i < 100000; i++) {

            queue.offer(i);

        }

    }

    static class Poll implements Runnable {

        public void run() {

            // while (queue.size()>0) {

            while (!queue.isEmpty()) {

                System.out.println(queue.poll());

            }

            latch.countDown();

        }

    }
}
