package string;

public class StringToLowerUpperCaseExample {
	public static void main(String[] args) {
		String str1 = "Java Programming";
		String str2 = "JAVA Programming";		
		
		System.out.println(str1.equals(str2));
		
		String lowerStr1 = str1.toUpperCase();		// �빮�ڷ�
		String lowerStr2 = str2.toLowerCase();		// �ҹ��ڷ�
		System.out.println(lowerStr1.equals(lowerStr2));
		
		System.out.println(str1.equalsIgnoreCase(str2));	// �ҹ��� �빮�� ���� ���Ͽ� ������ Ȯ��
		}
}
