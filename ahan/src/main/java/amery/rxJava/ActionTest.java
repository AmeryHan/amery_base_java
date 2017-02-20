package amery.rxJava;

import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by ahan on 14/02/2017.
 */
public class ActionTest {

	@Test
	public void test() {

		//rx 1.0

		Observable.just("Hello, world!")
			.subscribe(new Action1<String>() {
				@Override
				public void call(String s) {
					System.out.println(s);
				}
			});


		Observable.just("Hello, world!11111")
			.subscribe(s -> System.out.println(s));

		Observable.just("Hello, world!")
			.map(s -> s + " -Dan")
			.subscribe(s -> System.out.println(s));

		Observable.just("Hello, world!")
			.map(s -> s.hashCode())
			.subscribe(i -> System.out.println(Integer.toString(i)));

	}
}
