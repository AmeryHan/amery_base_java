package amery.jdk8.stream;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ahan on 23/12/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestStream {

	@Test
	public void convert_list_to_map_with_java8_lambda () {

		List<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie(1, "The Shawshank Redemption"));
		movies.add(new Movie(2, "The Godfather"));

		Map<Integer, Movie> mappedMovies = movies.stream().collect(
			Collectors.toMap(Movie::getRank, (p) -> p));

		//logger.info(mappedMovies);

		assertTrue(mappedMovies.size() == 2);
		assertEquals("The Shawshank Redemption", mappedMovies.get(1).getDescription());
	}

	@Test
	public void one_to_many() {
		Stream<List<Integer>> inputStream = Stream.of(
			Arrays.asList(1),
			Arrays.asList(2, 3),
			Arrays.asList(4, 5, 6)
		);

		Stream<Integer> outputStream = inputStream.
			flatMap(childList -> childList.stream());

		Assert.assertTrue(outputStream.collect(toList()).size() == 6);


		Stream<List<Integer>> _inputStream = Stream.of(
			Arrays.asList(1),
			Arrays.asList(2, 3),
			Arrays.asList(4, 5, 6)
		);
		Assert.assertTrue(_inputStream.collect(toList()).size() == 3);
	}

	@Test
	public void square() {
		List<Integer> nums = Arrays.asList(1, 2, 3, 4);
		List<Integer> squareNums = nums.stream().
			map(n -> n * n).
			collect(Collectors.toList());
		Assert.assertTrue(squareNums.get(3).equals(16));
	}

	@Test
	public void upperCase() {
		List<String> wordList = Lists.newArrayList("sed", "opi");
		List<String> output = wordList.stream().
			map(String::toUpperCase).
			collect(Collectors.toList());

		Assert.assertTrue(output.get(0).equals("SED"));
	}

	@Test
	public void leave() {
		Integer[] sixNums = {1, 2, 3, 4, 5, 6};
		Integer[] evens =
			Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);

		Assert.assertTrue(evens.length == 3);
	}

	@Test
	public void peek() {
		List list = Stream.of("one", "two", "three", "four")
			.filter(e -> e.length() > 3)
			.peek(e -> System.out.println("Filtered value: " + e))
			.map(String::toUpperCase)
			.peek(e -> System.out.println("Mapped value: " + e))
			.collect(Collectors.toList());


		Assert.assertTrue(list.size() == 2);
	}

	@Test
	public void reduce() {
		// 字符串连接，concat = "ABCD"
		String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
		// 求最小值，minValue = -3.0
		double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
		// 求和，sumValue = 10, 有起始值
		int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
		// 求和，sumValue = 10, 无起始值
		sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
		// 过滤，字符串连接，concat = "ace"
		concat = Stream.of("a", "B", "c", "D", "e", "F").
			filter(x -> x.compareTo("Z") > 0).
			reduce("", String::concat);

		Assert.assertTrue("a".compareTo("Z") > 0);
		Assert.assertTrue("A".compareTo("Z") < 0);
		Assert.assertTrue(null != concat);
	}


}
