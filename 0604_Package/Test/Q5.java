// [문제5] 다음의 for 문을 while 문으로 반경하시오.

public class Q5 {

	public static void main(String[] args) {
//		for (int i = 0; i <= 10; i++) {
//			for (int j = 0; j < i; j++) {
//				System.out.print("*");
//			}
//			System.out.println();
//		}
//		System.out.println("----------------");
		
		int i=0;
		while (i<=10) {
			int j=0;
			while (j<i) {
				System.out.print("*");
				j++;
			}
			System.out.println();
			i++;
		}
	}
}

// Key Word : for, while, 변환