//[����3] ������ ���� ���߱� ������ �ۼ��� ���̴�.
// 1�� 100 ������ ���� �ݺ������� �Է����� ��ǻ�Ͱ� ������ ���� ���߸� ������ ������. 
// ����ڰ� ���� �����ϸ�, ��ǻ�ʹ� �ڽ��� ������ ���� ���ؼ� ����� �˷��ش�. 
// ����ڰ� ��ǻ�Ͱ� ������ ���ڸ� ���߸� ������ ������ �� ������ ���ڸ� ������� �˷��ش�. 
// ( ) �ȿ� �˸��� �ڵ带 �־� ���α׷��� �ϼ��Ͻÿ�.
// do {} �κ� �ۼ�.

public class Q3 {

	public static void main(String[] args) {
		// 1~100������ ������ ���� �� answer�� �����Ѵ�.
		int answer = (int) (Math.random() * 100 + 1);
		int input = 0;
		int count = 0;
		// ȭ�����κ��� ����� �Է��� ��� ���ؼ� Scanner Ŭ���� ���
		java.util.Scanner s = new java.util.Scanner(System.in);
		do {
			count++;
			System.out.print("1�� 100������ ���� �Է��ϼ��� : ");
			input = s.nextInt(); // �Է� ���� ���� ���� input�� �����Ѵ�.
			
			if (answer > input) {
				System.out.println("�� ū ���� �Է��ϼ���.");
			} else if (answer < input) {
				System.out.println("�� ���� ���� �Է��ϼ���.");
			} else {
				System.out.println("������ϴ�. �õ�Ƚ���� "+count+"���Դϴ�.");
				break;
			}
			
		}while(true);

	}

}


// do ~ while �� Ȱ��, if �� Ȱ��, count, scanner