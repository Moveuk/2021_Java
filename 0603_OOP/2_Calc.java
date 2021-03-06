// 이것이 자바다 _ 신용권 : 203p ~ 
//--------------------------------------------- 
//      메소드 선언 형식

//	리턴타입 메소드명 (매개변수s...) {
//		
//		[return 리턴값;] // [void 일때 생략가능]
//	}
	
	// 리턴타입 
	// 1. void - 리턴값이 없다.
	// 2. 기본형타입(int, double, float,...)
	// 3. 참조형(배열, 클래스(ex:Car,Student,...), 인터페이스,...))
	
	// 궁금증. 리턴타입이 없으면 생성자 판정?
	// 클래스는 모든 변수를 초기화 해줘야하고, 메소드는 상관이 없음.

// Calc 예제 -------------------------------------
public class Calc {
	
  // Calc () 생성자는 없을 땐 자바에선 생성자가 자동생성됨.
  // 단, 상수와 같은 초기값이 존재하는 변수를 선언할 경우에는 반드시 초기화 및 생성자를 생성해주어야함.
  
	public void add() {
		System.out.println(3+5);
	}
	public void add(int x, int y) {
		System.out.println(x+y);
	}
	public void add(int x, int y, int z) {
		System.out.println(x+y+z);
	}
	public int addInt(int x, int y) { // add로 같은 메소드를 만들수가 없음. -> 오버로딩으로 이어짐.
		int sum = x + y;
		return sum;
	}
	public double addDouble(Double x, Double y) { // 바로 리턴 가능.
		return x + y;
	}
	
	// quiz ----------------------------------------------------
  
	// 빼기 기능 : 리턴 처리(정수형으로 처리)
	public int subs(int x, int y) {
		return x - y;
	}
	
	// 곱하기 기능 : 리턴 처리(정수형으로 처리)
	public int multiply(int x, int y) {
		return x * y;
	}
	
	// 나누기 기능 : 리턴 처리(실수형으로 처리)
	public double divide(double x, double y) {
		return x / y;
	}
	//-----------------------------------------------------------
	
	public int sum(int... values) { 
		// 가변형 매개 변수 - 가변 인자 : 변수의 값을 다양하게 넣을 수 있음.
		// 매개 변수를 배열로 받아옴.
		// (int타입의 값들을... values 라는 배열에 넣어 초기화시켜라.
		int sum = 0;
		for (int num : values) { // int num 을 values라는 배열을 돌려라.
			sum += num;
		}
		return sum;
	}		

}
