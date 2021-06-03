//  이것이 자바다 _ 신용권 : 
//---------------------------------------------------------------------------------------------------

		// 0603_5교시 이후 변수 수업. -----------------------
    
//		변수 	- 인스턴스 변수 (int iv)
// 				      - 생성자에 의한 초기화 가능.
//				      - 생성할 때마다 할당되어짐.
//
//			    - 클래스(static) 변수 (static int cv)
//				      - static 변수는 초기화가 불가능함.
//				      - 한번만 할당 되어짐(모든 객체가 공유한다.)
//			      	 ->클래스에서 여러 독립적인 인스턴스를 생성할 때 모든 인스턴스가 공통적으로 사용해야 하는 값에 대해선 static을 취한다. 
//		 		         반면에 독립적인 인스턴스에서 클래스의 변수나 메소드를 가져와 변동하여 사용할 경우엔 static을 쓰지 않는다.
//		 	           작성한 메서드 중에서 인스턴스 변수를 사용하지 않는 것에 대해선 static을 고려한다.
//
//		생성자
//		
//		메소드  - 인스턴스 메소드
//			      	 - void add() {}
//
//		        - 클래스(static) 메소드
//		      	   - static void add() {}
//		      		 - 객체의 생성없이 사용 가능.(클래스이름.메소드명())
//	          		인스턴트 스태틱 차이는 할당되어지는 시점의 차이가 생겨서 다름.
..
//		상수    - 변경이 불가한 변수 ( final double PI )
//		      	- 반드시 초기화해서 선언한다.
//		      	- 생성자를 통한 변경 가능.



public class Calc {

	// 메소드 오버로딩 overloading
	public class CalcOverload {
	   //오버로딩
	   //1.메소드의 이름이 같아야 한다.
	   //2.단 매개변수의 타입 또는 갯수가 달라야 한다.
	   void add() {
	      System.out.println(5+7);
	   }
	   //int x 매개변수를 넣어 갯수가 다르다
	   void add(int x) {
	      System.out.println(x);
	   }
	   //double x 매개변수를 넣어 타입이 다르게 했다
	   void add(double x) {
	      System.out.println(x);
	   }
	   //int x, int y 매개변수들을 넣어 갯수가 다르게 했다
	   void add(int x, int y) {
	      System.out.println(x);
	   }
	   //long y 를 넣어 타입이 다르게 했다
	   void add(int x,long y) {
	      System.out.println(x);
	   }
	   void add(long x, int y) {
	      System.out.println(x);
	   }

	}
	
// 237page example---------------
// String color;									인스턴스 필드
// void setColor(color) {this.color = color;}		인스턴스 메소드
// static int plus(int x, int y) {return x + y;}	정적 메소드
// static int minus(int x, int y) {return x - y;}	정적 메소드
//-> 인스턴스 메소드는 인스턴트 필드를 바꾸고 싶을때 사용 가능.

	
  
  // 정적(static) 변수, 인스턴스 변수의 차이 및 상수(final) 설정 방법
  
	static double pi = 3.14;
	static int plus(int a, int b) {
	return a + b;
	}
	int iv = 200;
	final static double PI = 3.14;
	final double GRAVITY;
	
	// 생성자틀 통한 상수 변경 방법.
	//	-> 인스턴스 만들때 초기값을 변경해서 사용하는 방법 인듯.
	Calc() {
		this(1234.1234);
	} 
	Calc(double Gravity) {
		this.GRAVITY = Gravity;
	} 


}
