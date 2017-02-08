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


}
