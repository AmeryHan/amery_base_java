package amery.guava;

import amery.entity.Room;
import amery.jdk8.stream.Person;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by ahan on 20/02/2017.
 */
public class GuavaTest {

	// http://ifeve.com/google-guava/
	@Test
	public void map2map() {

		Map<Integer, Person> map = Maps.newHashMap();
		map.put(1, new Person(1, "name1"));
		map.put(2, new Person(2, "name2"));

		Map output = Maps.transformValues(map, item -> item.getName());
		Assert.assertTrue(null != output);

	}

	@Test
	public void list2list() {

		List<Person> list = Lists.newArrayList();
		list.add(new Person(1, "name1"));
		list.add(new Person(2, "name2"));

		List<Room> rooms = FluentIterable.from(list).transform(person -> {
			Room room = Room.builder()
				.id(person.getNo())
				.name(person.getName())
				.build();
			return room;
		}).toList();

		Assert.assertTrue(null != rooms);

	}
}
