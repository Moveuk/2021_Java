package treemap;

import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapExample2 {
	public static void main(String[] args) {
		TreeMap<Integer, String> scores = new TreeMap<Integer, String>();
		scores.put(new Integer(87), "홍길동");
		scores.put(new Integer(98), "이동수");
		scores.put(new Integer(75), "박길순");
		scores.put(new Integer(95), "신용권");
		scores.put(new Integer(80), "김자바");

		NavigableMap<Integer, String> descendingMap = scores.descendingMap();
		Set<Map.Entry<Integer, String>> descendingEntrySet = descendingMap.entrySet();
		for (Map.Entry<Integer, String> entry : descendingEntrySet) {
			System.out.print(entry.getKey() + "-" + entry.getValue() + " ");
		}
		System.out.println();

		NavigableMap<Integer, String> ascendingMap = descendingMap.descendingMap();
		Set<Map.Entry<Integer, String>> ascendingEntrySet = ascendingMap.entrySet();
		// for문
		System.out.println("for 문");
		for (Map.Entry<Integer, String> entry : ascendingEntrySet) {
			System.out.print(entry.getKey() + "-" + entry.getValue() + " ");
		}
		System.out.println();

		Iterator<Map.Entry<Integer, String>> entryIterator = ascendingEntrySet.iterator();
		
		// while문
		System.out.println("while 문");
		while (entryIterator.hasNext()) {
			Map.Entry<Integer, String> entry = entryIterator.next();
			Integer key = entry.getKey();
			String value = entry.getValue();
			System.out.print(entry.getKey() + "-" + entry.getValue() + " ");

		}
	}
}
