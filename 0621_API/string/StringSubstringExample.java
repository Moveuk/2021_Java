package string;

public class StringSubstringExample {
	public static void main(String[] args) {	
		String ssn = "880815-1234567 ";
		
		String firstNum = ssn.substring(0, 6);	// 0(����) ~ 6(����) ������ ���ڿ��� �����ϴ� ��.
		System.out.println(firstNum);		
		
		String secondNum = ssn.substring(7);	// 7������ ���ڿ� ����
		System.out.println(secondNum);
	} 
}
