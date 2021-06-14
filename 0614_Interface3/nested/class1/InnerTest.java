package nested.class1;

public class InnerTest {

	public static void main(String[] args) {

		A a = new A();
		
		A.B b = a.new B();  //인스턴스한 내부클래스는 이렇게 생성 해야함.
		
		b.field = 3;
		b.method1();
		
		//------------------------------
		
		A.C c = new A.C();	// 정적 내부 클래스.
		c.field = 3;
		c.method1();
		A.C.field2 = 3 ; 	// 스태틱한 변수이기 때문에 인스턴스화 하지 않아도 호출 가능함.
		A.C.method2(); 		// 스태틱 메소드기 때문에 바로 호출 가능.

		//------------------------------
		
		a.method(); 		// 로컬 내부 클래스
		// 내부 클래스들을 실행 시키는 코드.
	}

}
