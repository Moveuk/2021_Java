package string;

public class StringIndexOfExample {
	public static void main(String[] args) {
		String subject = "자바 프로그래밍";
		
		int location = subject.indexOf("프로그래밍");	// "프로그래밍"이 들어간 첫 인덱스를 리턴한다.
		System.out.println(location);
		
		if(subject.indexOf("자바") != -1) {		// -1의 의미 : 못찾은 값에 대해서는 -1을 리턴한다.
			System.out.println("자바와 관련된 책이군요");
		} else {
			System.out.println("자바와 관련없는 책이군요");
		}
	}
}