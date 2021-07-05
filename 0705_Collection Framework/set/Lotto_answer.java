package set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Lotto_answer {

	public static void main(String[] args) {

		Set<Integer> lottoNums = new HashSet<>(6);
		
		Iterator<Integer> it = lottoNums.iterator();
		
		while (true) {
			int lotto = (int)(Math.random() * 45)+1;
			lottoNums.add(lotto);
			System.out.println(lotto);
			if (lottoNums.size() == 6) {
				break;
			}
		}
		System.out.println(lottoNums);
	}

}
