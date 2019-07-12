https://www.cnblogs.com/chanshuyi/p/head_first_of_reflection.html
第一种，使用 Class.forName 静态方法。当你知道该类的全路径名时，你可以使用该方法获取 Class 类对象。

Class clz = Class.forName("java.lang.String");
第二种，使用 .class 方法。

这种方法只适合在编译前就知道操作的 Class。

Class clz = String.class;
第三种，使用类对象的 getClass() 方法。

String str = new String("Hello");
Class clz = str.getClass();