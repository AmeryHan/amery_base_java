package amery.jdk.basic;

/**
 * Created by ahan on 25/07/2017.
 */
public class TestOOM {

	public static void main(String[] args) {
		long arr[] = {};
		for (int i=1; i<=10000000; i*=2) {
			arr = new long[i];

			System.out.println("size : " + i);
			Runtime runtime = Runtime.getRuntime();
			System.out.printf("maxMemory : %.2fM\n", runtime.maxMemory()*1.0/1024/1024);
			System.out.printf("totalMemory : %.2fM\n", runtime.totalMemory()*1.0/1024/1024);
			System.out.printf("freeMemory : %.2fM\n", runtime.freeMemory()*1.0/1024/1024);
		}


	}
}
