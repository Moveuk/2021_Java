package string;


public class StringCharAtExample {
	public static void main(String[] args) {
		String ssn = "010624-1230123";	// �ֹι�ȣ
		char sex = ssn.charAt(7);		// 7�ڸ� (0���ͽ���)
		switch (sex) {	// 1�� �������·� ��ȯ��. `1`
			case '1':
			case '3':
				System.out.println("���� �Դϴ�.");
				break;
			case '2':
			case '4':
				System.out.println("���� �Դϴ�.");
				break;
		}
	}
}

