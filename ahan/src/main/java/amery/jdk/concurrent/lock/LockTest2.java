package amery.jdk.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ameryhan
 * @date 2019/8/21 12:47
 */
public class LockTest2 extends Thread {
    ReentrantLock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }
    public void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }

    @Override
    public void run() {
        get();
    }
    public static void main(String[] args) {
        LockTest2 ss = new LockTest2();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
    }


}
