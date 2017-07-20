package amery.jdk.basic;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ahan on 13/07/2017.
 */
public class MyClassLoaderTest {

	/**
	 * http://www.jianshu.com/p/b09888ddac35
	 * Bootstrap ClassLoader是在JVM开始运行的时候加载java的核心类，是用C++编写的，它用来加载核心类库，在JVM源代码中这样写道：
	 static const char classpathFormat[] =
	 "%/lib/rt.jar:"
	 "%/lib/i18n.jar:"
	 "%/lib/sunrsasign.jar:"
	 "%/lib/jsse.jar:"
	 "%/lib/jce.jar:"
	 "%/lib/charsets.jar:"
	 "%/classes";
	 Extension ClassLoader是用来加载扩展类，即/lib/ext中的类。
	 AppClassLoader用来加载Classpath的类，是和我们关系最密切的类。
	 URLClassLoader用来加载网络上远程的类，暂且不讨论。
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		ClassLoader myLoader = new ClassLoader() {
			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				try {
					String fileName=name.substring(name.lastIndexOf(".")+1)+".class";
					InputStream is=getClass().getResourceAsStream(fileName);
					if( is == null ){
						return super.loadClass(name);
					}
					byte[] bytes = new byte[is.available()];
					is.read(bytes); //通过自定义类加载器读取class文件的二进制流
					return defineClass(name, bytes, 0,bytes.length);

				} catch (IOException e) {
					e.printStackTrace();
					throw new ClassNotFoundException(name);
				}
			}
		};


		Object obj = myLoader.loadClass("amery.jdk.basic.MyClassLoaderTest").newInstance();
		System.out.println(obj.getClass() );
		System.out.println(obj instanceof MyClassLoaderTest);

		System.out.println(myLoader.getParent().toString());

		//class loader 不一样，俩个class就不一样
	}
}
