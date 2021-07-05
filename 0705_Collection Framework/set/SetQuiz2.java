package set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetQuiz2 {

	public static void main(String[] args) {

		Set<String> set = new HashSet();
		
		set.add("1");
		set.add("2");
		set.add("3");
		set.add("4");
		set.add("5");
		set.add("6");
		set.add("7");
		set.add("8");
		set.add("9");
		set.add("10");
		
		Iterator<String> iterator = set.iterator();

		while (iterator.hasNext()) {
			String s = iterator.next();
			if (Integer.parseInt(s)%2==0) {
				iterator.remove();
			}
		}
		
		System.out.println("----결과 화면----");
		
		iterator = set.iterator();
		
		while (iterator.hasNext()) {
			String s = iterator.next();
			System.out.println(s);
		}
	}
}
