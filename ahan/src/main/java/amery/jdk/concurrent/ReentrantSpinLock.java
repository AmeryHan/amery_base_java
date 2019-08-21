package amery.jdk.concurrent;

import java.util.concurrent.atomic.AtomicReference;

public class ReentrantSpinLock {

    private AtomicReference<Thread> cas = new AtomicReference<>();
    private int count;

    public void lock() {
        Thread current = Thread.currentThread();
        if (current == cas.get()) { // 如果当前线程已经获取到了锁，线程数增加一，然后返回
            count++;
            return;
        }
        // 利用CAS
        while (!cas.compareAndSet(null, current)) {
            // DO nothing
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        //cas.compareAndSet(current, null);
        if (current == cas.get()) {
            if (count > 0) {
                count--;
            } else {
                cas.compareAndSet(current, null);
            }
        }

    }
}
