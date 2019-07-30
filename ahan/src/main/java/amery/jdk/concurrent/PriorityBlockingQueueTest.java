package amery.jdk.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PriorityBlockingQueueTest {

	//static Logger logger = LogManager.getLogger(QueueTest.class);

	static Random random = new Random(47);

	public static void main(String[] args) throws InterruptedException

	{

		PriorityBlockingQueue<PriorityEntity> queue = new PriorityBlockingQueue<PriorityEntity>();

		ExecutorService executor = Executors.newCachedThreadPool();

		executor.execute(new Runnable()

		{

			public void run()

			{

				int i = 0;

				while (true)

				{

					queue.put(new PriorityEntity(random.nextInt(10), i++));

					try

					{

						TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));

					}

					catch (InterruptedException e)

					{

						log.error("", e);

					}

				}

			}

		});

		executor.execute(new Runnable()

		{

			public void run()

			{

				while (true)

				{

					try

					{

						System.out.println("take-- " + queue.take() + " left:-- [" + queue.toString() + "]");

						try

						{

							TimeUnit.MILLISECONDS.sleep(random.nextInt(3000));

						}

						catch (InterruptedException e)

						{

							log.error("", e);

						}

					}

					catch (InterruptedException e)

					{

						log.error("", e);

					}

				}

			}

		});

		try

		{

			TimeUnit.SECONDS.sleep(5);

		}

		catch (InterruptedException e)

		{

			log.error("", e);

		}

	}

	static class PriorityEntity implements Comparable<PriorityEntity>

	{

		private static int count = 0;

		private int id = count++;

		private int priority;

		private int index = 0;

		public PriorityEntity(int _priority, int _index)

		{

			System.out.println("_priority : " + _priority);

			this.priority = _priority;

			this.index = _index;

		}

		public String toString()

		{

			return id + "# [index=" + index + " priority=" + priority + "]";

		}

		//数字小,优先级高

		public int compareTo(PriorityEntity o)

		{

			return this.priority > o.priority ? 1 : this.priority < o.priority ? -1 : 0;

		}

	}
}
