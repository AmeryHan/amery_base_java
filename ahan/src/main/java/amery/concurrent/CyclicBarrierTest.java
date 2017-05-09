package amery.concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
08
 * 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。在涉及一组固定大小的线程的程序中，
09
 * 这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。
10
 * 因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier。
11
 * CyclicBarrier 支持一个可选的 Runnable 命令，在一组线程中的最后一个线程到达之后（但在释放所有线程之前），
12
 * 该命令只在每个屏障点运行一次。若在继续所有参与线程之前更新共享状态，此屏障操作 很有用。
13
 * @author lei
14
 * 2011-11-15
15
 */

public class CyclicBarrierTest {

    public static void main(String[] args) {

        //新建一个线程池

        ExecutorService service = Executors.newCachedThreadPool();

        //创建一个新的 CyclicBarrier，它将在给定数量的参与者（线程）处于等待状态时启动

        final CyclicBarrier cb = new CyclicBarrier(3);

        for (int i = 0; i < 3; i++) {

            Runnable runnable = new Runnable() {

                public void run() {

                    try {

                        Thread.sleep((long) (Math.random() * 10000));

                        System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点1，当前已有" + (cb.getNumberWaiting() + 1) + "个已经到达，" + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));

                        //在所有参与者都已经在此 barrier上调用 await 方法之前，将一直等待

                        cb.await();

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                }

            };

            service.execute(runnable);

        }

        service.shutdown();

    }

}

