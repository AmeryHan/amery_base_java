package amery.jdk8.stream;

import java.util.HashMap;
import java.util.Map;

public class MutableDemo1 {

	public static void main(String[] args) {
		 
        // HashMap
        Map<MutableKey, String> map = new HashMap<>();
 
        // Object created
        MutableKey key = new MutableKey(10, 20);
 
        // Insert entry.
        map.put(key, "Robin");
 
        // This line will print 'Robin'
        System.out.println(map.get(key));
 
        // Object State is changed after object creation.
        // i.e. Object hash code will be changed.
        key.setI(30);
 
        // This line will print null as Map would be unable to retrieve the
        // entry.
        System.out.println(map.get(key));
    }
}
