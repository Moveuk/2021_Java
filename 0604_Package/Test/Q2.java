// [����2] �Ʒ��� ȭ��(Fahrenhrit)�� ����(Celcius)�� ��ȯ�ϴ� �ڵ��̴�. 
// ��ȯ������ 'C = 5/9 �� (F - 32)'��� �� ��, ( )�� �˸��� �ڵ带 �����ÿ�. 
// ��, ��ȯ ������� �Ҽ��� ��° �ڸ����� �ݿø��ؾ� �Ѵ�.
// (Math.round)�� ������� �ʰ� �ĸ��� ��.

public class Q2 {

	public static void main(String[] args) {
		int fahrenhrit = 100;
		float celcius = (float)(((fahrenhrit-32)*500+5)/9)/100;
		System.out.println("fahrenhrit:"+fahrenhrit);
		System.out.println("celcius:"+celcius);

	}

}

