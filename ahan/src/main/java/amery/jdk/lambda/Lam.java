package amery.jdk.lambda;

import java.util.function.Function;

public class Lam {

	public static void main(String[] args) {
		
		Thread t = new Thread(
				//\\run 没有参数传入，所以用 (), 后面用 {} 包起方法体
				() -> {
				 System.out.println("Hello from a thread in run");
				}
				);
				//通过上面两个代码的比较可以发现使用 Lambda 表达式可以简化代码，并提高代码的可读性。
				//为了进一步简化 Lambda 表达式，可以使用方法引用。例如，下面三种分别是使用内部类，使用 Lambda 表示式和使用方法引用方式的比较：
				//1. 使用内部类
				Function<Integer, String> f = new Function<Integer,String>(){
				@Override
				public String apply(Integer t) {
				return null;
				}
				};
				//2. 使用 Lambda 表达式
				Function<Integer, String> f2 = t1 -> String.valueOf(t1);
				//3. 使用方法引用的方式
				Function<Integer, String> f1 = String::valueOf;

	}

}
