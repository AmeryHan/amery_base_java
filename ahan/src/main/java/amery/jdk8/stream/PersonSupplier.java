package amery.jdk8.stream;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Created by ahan on 08/02/2017.
 */
public class PersonSupplier implements Supplier<Person> {

	private int index = 0;
	private Random random = new Random();
	@Override
	public Person get() {
		return new Person(index++, "StormTestUser" + index, random.nextInt(100));
	}
}