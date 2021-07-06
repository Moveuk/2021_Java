package treeset;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest {

	public static void main(String[] args) {

		Set<Integer> set = new TreeSet<>();
		
		for (int i = 0; 6 > set.size(); i++) {
			int num = (int)(Math.random() * 45) + 1; 
			set.add(num);
		}
		
		System.out.println(set);
		
		
		
	}

}
