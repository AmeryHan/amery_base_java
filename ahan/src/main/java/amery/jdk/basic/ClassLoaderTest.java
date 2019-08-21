package amery.jdk.basic;

public class ClassLoaderTest {

    //类装载器有载入类的需求时，会先请示其Parent使用其搜索路径帮忙载入，如果Parent 找不到,那么才由自己依照自己的搜索路径搜索类
    //https://www.cnblogs.com/doit8791/p/5820037.html
    public static void main(String[] arg) {

        ClassLoader c = ClassLoaderTest.class.getClassLoader();  //获取Test类的类加载器

        System.out.println(c);

        ClassLoader c1 = c.getParent();  //获取c这个类加载器的父类加载器

        System.out.println(c1);

        ClassLoader c2 = c1.getParent();//获取c1这个类加载器的父类加载器

        System.out.println(c2);

    }
}
