package amery.jdk.basic;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ahan on 13/07/2017.
 */
public class MyClassLoaderTest {

	/**
	 * http://www.jianshu.com/p/b09888ddac35
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
	}
}
