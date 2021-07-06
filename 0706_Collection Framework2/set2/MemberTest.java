package set2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MemberTest {

	public static void main(String[] args) {

		Set<Member> set = new HashSet<>();
		
		Member m1 = new Member("ȫ�浿", 10);
		Member m2 = new Member("ȫ�浿", 10);
		
		set.add(m1);
		set.add(m2);
		
		Iterator<Member> it = set.iterator();
		
		while (it.hasNext()) {
			Member m = it.next();
			System.out.println(m.name);
			System.out.println(m.age);
		}
	}

}
