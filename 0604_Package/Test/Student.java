//[문제9] Student 클래스에 6개의 속성과 한 개의 메서드 getTotal()를 정의하시오.

public class Student {

		String name;
		int classroom;
		int number;
		int korean;
		int english;
		int math;
		
		private int getTotal() {
			return korean + english + math;
		}
	
}

// Key Word: 클래스 선언, 필드 선언, 메소드 선언, 메소드 초기화, 메소드 리턴 타입 