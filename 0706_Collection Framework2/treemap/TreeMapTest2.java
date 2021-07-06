package treemap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapTest2 {

	public static void main(String[] args) {
		// 맵 컬렉션 생성
		Map<String, Integer> map = new TreeMap<>(new ValueComparator());

		// 객체 저장
		map.put("신용권", 85);
		map.put("홍길동", 90);
		map.put("동장군", 80);
		map.put("홍길동", 95);
		
		System.out.println(map);

		Iterator it = map.entrySet().iterator();
		
		System.out.println("===기본정렬===");
		
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			int value = ((Integer)entry.getValue()).intValue();
			System.out.println(entry.getKey()+" : "+value);
		}
		
		System.out.println(map);
		
		System.out.println();
		
		Set<String> keySet = map.keySet();
		Iterator<String> keyIterator = keySet.iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			Integer value = map.get(key);
			System.out.println("\t" + key + " : " + value);
		}
		System.out.println();
		

		Iterator<String> keyIterator2 = keySet.iterator();
		
		while (keyIterator2.hasNext()) {
			String key = keyIterator2.next();
			Integer value = map.get(key);
			System.out.println("\t" + key + " : " + value);
		}
	}

	static class ValueComparator implements Comparator {

		@Override
		public int compare(Object o1, Object o2) {
			if (o1 instanceof Map.Entry && o2 instanceof Map.Entry) {
				Map.Entry e1 = (Map.Entry) o1;
				Map.Entry e2 = (Map.Entry) o2;

				int v1 = ((Integer) e1.getValue()).intValue();
				int v2 = ((Integer) e2.getValue()).intValue();
				return v2 - v1; // 내림차순
			}
			return -1;
		}
	}
}
