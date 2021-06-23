//[문제8] 2차원 배열 arr에 담긴 모든 값의 총합과 평균을 구하는 프로그램을 완성하시오.
// for 문 작성.
public class Q8 {

	public static void main(String[] args) {
		int[][] arr = {
				{ 5, 5, 5, 5, 5},
				{10,10,10,10,10},
				{20,20,20,20,20},
				{30,30,30,30,30}
		};
		int total = 0;
		float avg = 0;
		for (int[] is : arr) {
			for (int is2 : is) {
				total += is2;
				avg = total/(float)(arr[1].length*arr.length);
			}
		}
		System.out.println("total="+total);
		System.out.println("avg="+avg);
	}

}

// Key Word : 2차원 배열