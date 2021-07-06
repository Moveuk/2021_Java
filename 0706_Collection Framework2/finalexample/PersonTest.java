package finalexample;

import java.util.Iterator;
import java.util.TreeSet;

public class PersonTest {

	public static void main(String[] args) {

		TreeSet<Person> treeSet = new TreeSet<>();
		
		treeSet.add(new Person("홍길동",10,"서울"));
		treeSet.add(new Person("김자동",12,"대전"));
		treeSet.add(new Person("고길동",14,"부산"));

		Iterator<Person> it = treeSet.iterator();
		
		while (it.hasNext()) {
			Person p = it.next();
			System.out.println(p.name+"  " + p.age+"  "+p.address);
		}
		
		// 아이디어 노트 : String 구분 방법
//		String aString = "a";
//		String bString = "b";
//		
//		System.out.println(aString.compareTo(bString));

	}

}
