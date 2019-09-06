package amery.jdk.basic;

/**
 * @author ameryhan
 * @date 2019/9/4 19:50
 */
public class Singleton2 {
    private static class SingletonHolder {
        public static Singleton2 instance = new Singleton2();
    }

    private Singleton2() {
    }

    public static Singleton2 newInstance() {
        return SingletonHolder.instance;
    }
}

