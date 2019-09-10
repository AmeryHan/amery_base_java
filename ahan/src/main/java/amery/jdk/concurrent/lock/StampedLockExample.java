package amery.jdk.concurrent.lock;

import org.junit.Test;

import java.util.concurrent.locks.StampedLock;

/**
 * @author ameryhan
 * @date 2019/9/9 17:00
 */
public class StampedLockExample {

    private final StampedLock stampedLock = new StampedLock();

    @Test
    // 悲观读锁
    public void pessimisticReadLockTest() {
        long stamp = stampedLock.readLock();
        try {
            // 业务逻辑
        } finally {
            stampedLock.unlockRead(stamp);
        }
    }

    @Test
    // 写锁
    public void writeLockTest() {
        long stamp = stampedLock.writeLock();
        try {
            // 业务逻辑
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    private int x, y;
    //private final StampedLock stampedLock = new StampedLock();

    // 计算到原点的距离
    public double distanceFromOrigin() {
        // 乐观锁（无锁算法，共享变量x和y读入方法局部变量时，x和y有可能被其他线程修改）
        long stamp = stampedLock.tryOptimisticRead();
        // 读入局部变量，读的过程中，数据可能被修改
        int curX = x;
        int curY = y;
        // 判断执行读操作期间，是否存在写操作，如果存在，validate会返回false
        if (!stampedLock.validate(stamp)) {
            // 升级为悲观读锁
            // 如果不升级，有可能反复执行乐观读，浪费大量CPU
            stamp = stampedLock.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                // 释放悲观读锁
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(curX * curX + curY * curY);
    }
}
