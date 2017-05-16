package amery.jdk.basic;

/**
 * Created by ahan on 16/05/2017.
 */
public class Singleton {

	private volatile static Singleton instance = null;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null)
					instance = new Singleton();
			}
		}
		return instance;
	}

	/**
	 * System volatile Console cons
	 * Thread name
	 * BigDecimal BIG_TEN_POWERS_TABLE
	 * File private volatile transient Path filePath; toPath
	 */
}
