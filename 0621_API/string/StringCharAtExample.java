package string;


public class StringCharAtExample {
	public static void main(String[] args) {
		String ssn = "010624-1230123";	// 주민번호
		char sex = ssn.charAt(7);		// 7자리 (0부터시작)
		switch (sex) {	// 1이 문자형태로 반환됨. `1`
			case '1':
			case '3':
				System.out.println("남자 입니다.");
				break;
			case '2':
			case '4':
				System.out.println("여자 입니다.");
				break;
		}
	}
}

