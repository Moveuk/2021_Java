package finalexample;

import java.util.Iterator;
import java.util.TreeSet;

public class PersonTest {

	public static void main(String[] args) {

		TreeSet<Person> treeSet = new TreeSet<>();
		
		treeSet.add(new Person("ȫ�浿",10,"����"));
		treeSet.add(new Person("���ڵ�",12,"����"));
		treeSet.add(new Person("��浿",14,"�λ�"));

		Iterator<Person> it = treeSet.iterator();
		
		while (it.hasNext()) {
			Person p = it.next();
			System.out.println(p.name+"  " + p.age+"  "+p.address);
		}
		
		// ���̵�� ��Ʈ : String ���� ���
//		String aString = "a";
//		String bString = "b";
//		
//		System.out.println(aString.compareTo(bString));

	}

}
