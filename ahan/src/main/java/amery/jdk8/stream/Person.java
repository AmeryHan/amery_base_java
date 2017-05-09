package amery.jdk8.stream;

import lombok.Builder;
import lombok.Data;

/**
 * Created by ahan on 08/02/2017.
 */
@Data
@Builder
public class Person {

	public int no;
	private String name;
	private int age;
	public Person (int no, String name) {
		this.no = no;
		this.name = name;
	}
	public Person (int no, String name, int age) {
		this.no = no;
		this.name = name;
		this.age = age;
	}
	public String getName() {
		//System.out.println(name);
		return name;
	}
}
