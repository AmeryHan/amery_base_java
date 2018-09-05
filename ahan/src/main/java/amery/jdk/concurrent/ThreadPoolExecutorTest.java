package amery.jdk.concurrent;

import java.util.concurrent.*;

public class ThreadPoolExecutorTest {

	//https://www.toutiao.com/i6597175542317842951/


	public static ExecutorService newCachedThreadPool() {

		return new ThreadPoolExecutor(0, Integer.MAX_VALUE,

			60L, TimeUnit.SECONDS,

			new SynchronousQueue<Runnable>());

	}

	public static void main(String[] args) {

		//cache();
		schedule();
	}

	public static void schedule() {

		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

			public void run() {

				System.out.println("delay 1 seconds, and excute every 3 seconds");

			}

		}, 1, 3, TimeUnit.SECONDS);

	}

	private static void cache() {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

		for (int i = 0; i < 10; i++) {

			final int index = i;

			try {

				Thread.sleep(index * 1000);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

			cachedThreadPool.execute(new Runnable() {

				public void run() {

					System.out.println(index);

				}

			});

		}
	}

}
