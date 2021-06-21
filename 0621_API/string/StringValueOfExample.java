package string;

public class StringValueOfExample {
	public static void main(String[] args) {
		String str1 = String.valueOf(10);		// 해당 타입을 문자열로 바꾸어줌. `10`
		String str2 = String.valueOf(10.5);		// 해당 타입을 문자열로 바꾸어줌. `10.5`
		String str3 = String.valueOf(true);		// 해당 타입을 문자열로 바꾸어줌. `true`	
		
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
	}
}
