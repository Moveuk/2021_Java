package treemap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;

public class TreeMapTest {

	public static void main(String[] args) {
		// �� �÷��� ����
		Map<String, Integer> map = new TreeMap<String, Integer>(new ValueComparator<T>);

		// ��ü ����
		map.put("�ſ��", 85);
		map.put("ȫ�浿", 90);
		map.put("���屺", 80);
		map.put("ȫ�浿", 95);
		
		System.out.println(map);

		Set<String> keySet = map.keySet();
		Iterator<String> keyIterator = keySet.iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			Integer value = map.get(key);
			System.out.println("\t" + key + " : " + value);
		}
		System.out.println();
		

		Iterator<String> keyIterator2 = keySet.iterator();
		
		while (keyIterator.hasNext()) {
			String key = keyIterator2.next();
			Integer value = map.get(key);
			System.out.println("\t" + key + " : " + value);
		}
	}
	
	static class ValueComparator<T> implements Comparator<T> {

		@Override
		public int compare(T o1, T o2) {

			return ((String)o1).compareTo((String)o2); 

		}
	}
}
