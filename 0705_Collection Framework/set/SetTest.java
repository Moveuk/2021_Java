package set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetTest {

	public static void main(String[] args) {

		Set<String> set = new HashSet();
		
		set.add("3");
		for (int i = 1; i < 3; i++) {
			set.add(String.valueOf(i));
		}
		set.add("7");
		set.add("4");
		
		Iterator<String> iterator = set.iterator();

		while (iterator.hasNext()) {
			String s = iterator.next();
			System.out.println(s);
		}
		
		System.out.println("--------------");
		
		iterator = set.iterator();
		
		while (iterator.hasNext()) {
			String s = iterator.next();
			System.out.println(s);
		}
	}
}
