package amery.jdk.base.proxy;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.takesArguments;

/**
 * 2 * @Author: han,zhansheng
 * 3 * @Date: 2019/10/21 10:37 AM
 * 4
 */
@Slf4j
public class ByteBuddyTest {

    @Test
    public void test() throws Exception {
        String helloWorld = new ByteBuddy().subclass(Db.class)
                .method(named("hello"))
                .intercept(MethodDelegation.to(Interceptor.class))
                .make()
                .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                .getLoaded()
                .newInstance()
                .hello("World");

        System.out.println(helloWorld);
    }

    @Test
    public void test_new_method() throws Exception {
        Class<? extends Foo> loaded = new ByteBuddy()
                .subclass(Foo.class)
                .defineMethod("doo", // 方法名
                        String.class, Modifier.PUBLIC)
                .withParameter(String.class, "s") // 方法参数
                .intercept(FixedValue.value("Zero!")) // 方法的具体实现
                .make()
                .load(ByteBuddyTest.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                .getLoaded(); // 获取加载后的Class

        Foo dynamicFoo = loaded.newInstance(); // 反射

        Method m = loaded.getDeclaredMethod("doo", String.class);
        System.out.println(m.invoke(dynamicFoo, ""));
    }

    @Test
    public void test2() throws Exception {
        String toString = new ByteBuddy().subclass(Object.class).name("example.Type") // 创建一个名为"example.Type"类
                .method(ElementMatchers.named("toString")) // 拦截其中的toString()方法
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(ByteBuddyTest.class.getClassLoader()) // 加载这个类
                .getLoaded()
                .newInstance() // Java 反射创建实例
                .toString(); //
        System.out.println("----" + toString);
    }

    @Test
    public void test3() throws Exception {
        Foo dynamicFoo = new ByteBuddy()
                .subclass(Foo.class)
                .method(isDeclaredBy(Foo.class)) // 匹配Foo中所有的方法
                .intercept(FixedValue.value("One!"))
                .method(named("foo")) // 匹配名为foo的方法
                .intercept(FixedValue.value("Two!"))
                .method(named("foo").and(takesArguments(1))) // 匹配名为foo且只有一个参数的方法
                .intercept(FixedValue.value("Three!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded()
                .newInstance();

        dynamicFoo.bar();
        dynamicFoo.foo();
        dynamicFoo.foo("aaaa");

        String a = "2";
    }

    // 执行完原始构造方法，再开始执行拦截器的代码
    // 拦截所有构造方法
    // 拦截的操作：首先调用目标对象的构造方法，根据前面自动匹配，
    // 这里直接匹配到参数为String.class的构造方法
    @Test
    public void test_constructor() throws Exception {
        Constructor<? extends Db> constructor = new ByteBuddy()
                .subclass(Db.class)
                .constructor(any())
                .intercept(SuperMethodCall.INSTANCE.andThen(
                        MethodDelegation.withDefaultConfiguration().to(new Interceptor())
                ))
                .make()
                .load(ByteBuddyTest.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                .getLoaded()
                .getConstructor(String.class);

        constructor.newInstance("mysql");
    }
}
