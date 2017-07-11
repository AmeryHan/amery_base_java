package amery.jdk.concurrent;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ahan on 11/07/2017.
 */
public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		int totalThread = 3;
		long start = System.currentTimeMillis();
		CountDownLatch countDown = new CountDownLatch(totalThread);
		for(int i = 0; i < totalThread; i++) {
			final String threadName = "Thread " + i;
			new Thread(() -> {
				System.out.println(String.format("%s\t%s %s", new Date(), threadName, "started"));
				try {
					Thread.sleep(1000);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				countDown.countDown();
				System.out.println(String.format("%s\t%s %s", new Date(), threadName, "ended"));
			}).start();;
		}
		countDown.await();
		long stop = System.currentTimeMillis();
		System.out.println(String.format("Total time : %sms", (stop - start)));
	}

	/**
	 * 可以看到，主线程等待所有3个线程都执行结束后才开始执行。
	 *
	 */

}
