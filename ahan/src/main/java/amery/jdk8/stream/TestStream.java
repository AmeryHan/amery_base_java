package amery.jdk8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ahan on 23/12/2016.
 */
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

}
