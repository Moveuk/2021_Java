package string;

public class StringToLowerUpperCaseExample {
	public static void main(String[] args) {
		String str1 = "Java Programming";
		String str2 = "JAVA Programming";		
		
		System.out.println(str1.equals(str2));
		
		String lowerStr1 = str1.toUpperCase();		// 대문자로
		String lowerStr2 = str2.toLowerCase();		// 소문자로
		System.out.println(lowerStr1.equals(lowerStr2));
		
		System.out.println(str1.equalsIgnoreCase(str2));	// 소문자 대문자 무시 비교하여 같은지 확인
		}
}
