package string;

public class StringIndexOfExample {
	public static void main(String[] args) {
		String subject = "�ڹ� ���α׷���";
		
		int location = subject.indexOf("���α׷���");	// "���α׷���"�� �� ù �ε����� �����Ѵ�.
		System.out.println(location);
		
		if(subject.indexOf("�ڹ�") != -1) {		// -1�� �ǹ� : ��ã�� ���� ���ؼ��� -1�� �����Ѵ�.
			System.out.println("�ڹٿ� ���õ� å�̱���");
		} else {
			System.out.println("�ڹٿ� ���þ��� å�̱���");
		}
	}
}