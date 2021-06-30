package reason_4_lambda;

public class StringConcatTest {

	public static void main(String[] args) {

		StringConcatImpl sc = new StringConcatImpl();
		String s1 = "java ";
		String s2 = "Version 10";
		
		System.out.println("클래스를 이용한 방식 : 인터페이스 + 구현 클래스 + 테스트 클래스");
		sc.makeString(s1, s2);
				
		System.out.println("람다식를 이용한 방식 : 인터페이스 + 람다식 테스트 클래스");
		StringConcat scfi = (a, b) -> {
			System.out.println(a + b);
		};
		
		scfi.makeString(s1, s2);
		
		System.out.println("");
		System.out.println("람다식을 활용하게 되면 중간의 구현 클래스가 필요 없어져 효율이 증가한다.");
	}
}
