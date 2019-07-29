https://www.cnblogs.com/chanshuyi/p/head_first_of_reflection.html
第一种，使用 Class.forName 静态方法。当你知道该类的全路径名时，你可以使用该方法获取 Class 类对象。

Class clz = Class.forName("java.lang.String");
第二种，使用 .class 方法。

这种方法只适合在编译前就知道操作的 Class。

Class clz = String.class;
第三种，使用类对象的 getClass() 方法。

String str = new String("Hello");
Class clz = str.getClass();

下面我们来看看 JDK 的 invoke 方法到底做了些什么。

进入 Method 的 invoke 方法我们可以看到，一开始是进行了一些权限的检查，最后是调用了 MethodAccessor 类的 invoke 方法进行进一步处理，如下图红色方框所示。



那么 MethodAccessor 又是什么呢？

其实 MethodAccessor 是一个接口，定义了方法调用的具体操作，而它有三个具体的实现类：

sun.reflect.DelegatingMethodAccessorImpl
sun.reflect.MethodAccessorImpl
sun.reflect.NativeMethodAccessorImpl

Inflation 机制。初次加载字节码实现反射，使用 Method.invoke() 和 Constructor.newInstance() 加载花费的时间是使用原生代码加载花费时间的 3 - 4 倍。
这使得那些频繁使用反射的应用需要花费更长的启动时间。