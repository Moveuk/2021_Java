// [문제7] 1차 배열 arr에 정수 값 5개를 초기회하고 모든 값을 더하는 프로그램을 완성하시오.
// 배열의 초기값 { 10, 20, 30, 40, 50 }

public class Q7 {

	public static void main(String[] args) {
		int[] arr = { 10, 20, 30, 40, 50 };
		int sum = 0;

		for (int num : arr) {
			sum += num;
		}

		System.out.println("sum=" + sum);
	}

}

// Key Word : 배열(array), 초기화, sum