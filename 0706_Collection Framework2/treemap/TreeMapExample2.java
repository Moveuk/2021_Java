package treemap;

import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapExample2 {
	public static void main(String[] args) {
		TreeMap<Integer, String> scores = new TreeMap<Integer, String>();
		scores.put(new Integer(87), "ȫ�浿");
		scores.put(new Integer(98), "�̵���");
		scores.put(new Integer(75), "�ڱ��");
		scores.put(new Integer(95), "�ſ��");
		scores.put(new Integer(80), "���ڹ�");

		NavigableMap<Integer, String> descendingMap = scores.descendingMap();
		Set<Map.Entry<Integer, String>> descendingEntrySet = descendingMap.entrySet();
		for (Map.Entry<Integer, String> entry : descendingEntrySet) {
			System.out.print(entry.getKey() + "-" + entry.getValue() + " ");
		}
		System.out.println();

		NavigableMap<Integer, String> ascendingMap = descendingMap.descendingMap();
		Set<Map.Entry<Integer, String>> ascendingEntrySet = ascendingMap.entrySet();
		// for��
		System.out.println("for ��");
		for (Map.Entry<Integer, String> entry : ascendingEntrySet) {
			System.out.print(entry.getKey() + "-" + entry.getValue() + " ");
		}
		System.out.println();

		Iterator<Map.Entry<Integer, String>> entryIterator = ascendingEntrySet.iterator();
		
		// while��
		System.out.println("while ��");
		while (entryIterator.hasNext()) {
			Map.Entry<Integer, String> entry = entryIterator.next();
			Integer key = entry.getKey();
			String value = entry.getValue();
			System.out.print(entry.getKey() + "-" + entry.getValue() + " ");

		}
	}
}
