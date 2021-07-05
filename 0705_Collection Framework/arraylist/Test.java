package arraylist;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Test {

	public static void main(String[] args) {

		List<String> list = new ArrayList();
		
		list.add("1");
		list.add("3");
		list.add("5");
		list.add("2");
		list.add("4");
		list.add("4");
		
		int sum = 0; 
		for (int i = 0; i < list.size(); i++) {
			sum += Integer.parseInt(list.get(i));
		}
		System.out.println(sum);
		
		List<Integer> list2 = new ArrayList();

		list2.add(new Integer(10));     // 굳이 이렇게 캐스팅 해줄 필요 없음. 오토박싱 되기 때문
		list2.add(20);
		list2.add(30);
		list2.add(40);
		list2.add(50);
		
		
		int sum2 = 0; 
		for (int i = 0; i < list2.size(); i++) {
			sum2 += list2.get(i);
		}
		System.out.println(sum2);
	}
}










































