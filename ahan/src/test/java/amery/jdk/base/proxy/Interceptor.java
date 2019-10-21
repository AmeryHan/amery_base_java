package amery.jdk.base.proxy;

/**
 * 2 * @Author: han,zhansheng
 * 3 * @Date: 2019/10/21 4:21 PM
 * 4
 */
public class Interceptor {

    public static String intercept(String name) {
        return "String";
    }

    public static String intercept(int i) {
        return "int";
    }

    public static String intercept(Object o) {
        return "object";
    }

}

