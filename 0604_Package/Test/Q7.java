// [����7] 1�� �迭 arr�� ���� �� 5���� �ʱ�ȸ�ϰ� ��� ���� ���ϴ� ���α׷��� �ϼ��Ͻÿ�.
// �迭�� �ʱⰪ { 10, 20, 30, 40, 50 }

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

// Key Word : �迭(array), �ʱ�ȭ, sum