package amery.jdk.concurrent.lock;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {

	/**
	 * 自旋锁（spinlock）：是指当一个线程在获取锁的时候，如果锁已经被其它线程获取，那么该线程将循环等待，然后不断的判断锁是否能够被成功获取，直到获取到锁才会退出循环。
	 *
	 */

	private AtomicReference<Thread> cas = new AtomicReference<Thread>();

	public void lock() {

		Thread current = Thread.currentThread();

		// 利用CAS

		while (!cas.compareAndSet(null, current)) {

			// DO nothing

		}

	}

	public void unlock() {

		Thread current = Thread.currentThread();

		cas.compareAndSet(current, null);

	}
}
