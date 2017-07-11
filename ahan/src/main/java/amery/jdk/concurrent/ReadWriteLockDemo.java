package amery.jdk.concurrent;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by ahan on 11/07/2017.
 */
public class ReadWriteLockDemo {

	public static void main(String[] args) {
		ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
		new Thread(() -> {
			readWriteLock.readLock().lock();
			try {
				System.out.println(new Date() + "\tThread 1 started with read lock");
				try {
					Thread.sleep(2000);
				} catch (Exception ex) {
				}
				System.out.println(new Date() + "\tThread 1 ended");
			} finally {
				readWriteLock.readLock().unlock();
			}
		}).start();
		new Thread(() -> {
			readWriteLock.readLock().lock();
			try {
				System.out.println(new Date() + "\tThread 2 started with read lock");
				try {
					Thread.sleep(2000);
				} catch (Exception ex) {
				}
				System.out.println(new Date() + "\tThread 2 ended");
			} finally {
				readWriteLock.readLock().unlock();
			}
		}).start();
		new Thread(() -> {
			Lock lock = readWriteLock.writeLock();
			lock.lock();
			try {
				System.out.println(new Date() + "\tThread 3 started with write lock");
				try {
					Thread.sleep(2000);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				System.out.println(new Date() + "\tThread 3 ended");
			} finally {
				lock.unlock();
			}
		}).start();
	}

	/**
	 * 从上面的执行结果可见，thread 1和thread 2都只需获得读锁，因此它们可以并行执行。而thread 3因为需要获取写锁，必须等到thread 1和thread 2释放锁后才能获得锁。
	 */
}
