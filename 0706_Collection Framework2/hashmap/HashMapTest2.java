package hashmap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapTest2 {
	public static void main(String[] args) {
		Map<Student, Integer> map = new HashMap<Student, Integer>();
		
		map.put(new Student(1, "È«±æµ¿"), 95);
		map.put(new Student(1, "È«±æµ¿"), 95);
		
		System.out.println("ÃÑ Entry ¼ö: " + map.size());
	}
}

