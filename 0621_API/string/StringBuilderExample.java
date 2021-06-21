package string;

public class StringBuilderExample {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();	// 멀티 스레드 환경이 아니면 굳이 버퍼를 쓸 이유가 없다.
		// 버퍼로 바꾸어도 아래 기능은 똑같다.
		
		sb.append("Java ");
		sb.append("Program Study");		
		// 기존 String은 기존 메모리를 무시하고 새로운 메모리를 할당하지만 append는 기존 메모리에 추가한다.
		// 따라서 메모리 효율성이 증가한다.
		System.out.println(sb.toString());	// StringBuilder는 String 타입이 아니므로 반드시 사용전 toString()으로 타입을 바꾸어줘야 한다.
		
		sb.insert(4, "2");
		System.out.println(sb.toString());

		sb.setCharAt(4, '6');		// 5번째를 6으로 바꾸어라(2를 6으로)
		System.out.println(sb.toString());
		
		sb.replace(6, 13, "Book");	// 지정한 칸수(7칸)와 상관없이 book으로 바꾼다.
		System.out.println(sb.toString());
		
		sb.delete(4, 5);			// 삭제도 범위로 삭제 가능하다.
		System.out.println(sb.toString());
		
		int length = sb.length();
		System.out.println("총문자수: " + length);
		
		String result = sb.toString();
		System.out.println(result);		
	}
}
