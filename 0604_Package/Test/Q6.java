//[����6] ������ ȸ������ ���ϴ� ���α׷��̴�. 
// ȸ����(Palindrome)��, ���ڸ� �Ųٷ� �о ������ �д� �Ͱ� ���� ���� ���Ѵ�. 
// ���� �迬 '12321'�̳� '13531'���� ���� ���Ѵ�. 
// ( )�� �˸��� �ڵ带 �־ ���α׷��� �ϼ��Ͻÿ�
// while if �� �ϼ�.


public class Q6 {

	public static void main(String[] args) {
		int number = 12321;
		int tmp =number;
		int result = 0;
		
		while (tmp!=0) {
			result = result*10 + tmp % 10;
			tmp /= 10;
		}
		
		if (number==result) {
			System.out.println(number + "�� ȸ���� �Դϴ�.");
		} else {
			System.out.println(number + "�� ȸ������ �ƴմϴ�.");
		}

	}

}

// Key Word : while, if, reverse, ȸ����, swap