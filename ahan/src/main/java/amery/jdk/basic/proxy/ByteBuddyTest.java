package amery.jdk.basic.proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import org.aopalliance.intercept.Interceptor;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * 2 * @Author: han,zhansheng
 * 3 * @Date: 2019/10/21 10:37 AM
 * 4
 */
public class ByteBuddyTest {

    public static void main(String[] args) {
        test();
    }

    class Foo {
        public String bar() { return null; }
        public String foo() { return null; }
        public String foo(Object o) { return null; }
    }

    class DB {
        public String hello(String name) {
            System.out.println("DB:" + name);
            return null;
        }
    }

    private static void test() {
        try {
            String helloWorld = new ByteBuddy().subclass(DB.class)
                    .method(named("hello"))
                    .intercept(MethodDelegation.to(Interceptor.class))
                    .make()
                    .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                    .getLoaded()
                    .newInstance()
                    .hello("World");

            System.out.println(helloWorld);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
