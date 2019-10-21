package amery.jdk.base.proxy;

/**
 * 2 * @Author: han,zhansheng
 * 3 * @Date: 2019/10/21 4:05 PM
 * 4
 */
public class Foo {

    public String bar() {
        System.out.println("aaaa");
        return null;
    }

    public String foo() {
        System.out.println("bbbb");
        return null;
    }

    public String foo(Object o) {
        System.out.println("xxxxx");
        return null;
    }

}
