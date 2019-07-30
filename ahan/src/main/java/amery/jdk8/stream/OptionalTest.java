package amery.jdk8.stream;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by ahan on 08/02/2017.
 */
public class OptionalTest {

	@Test
	public void test() {
		String strA = " abcd ", strB = null;
		print(strA);
		print("");
		print(strB);
		getLength(strA);
		getLength("");
		int length = getLength(strB);
		Assert.assertTrue(-1 == length);
	}


	public static void print(String text) {
		// Java 8
		Optional.ofNullable(text).ifPresent(System.out::println);
		// Pre-Java 8
		if (text != null) {
			System.out.println(text);
		}
	}
	public static int getLength(String text) {
		// Java 8
		return Optional.ofNullable(text).map(String::length).orElse(-1);
		// Pre-Java 8
		// return if (text != null) ? text.length() : -1;
	}
}
