// 이것이 자바다 _ 신용권 : 248p ~
// Key Word : 패키지(Package), 접근 제한자(Access Modifier), public, protected, default, private, 
//------------------------------------

package access.package1;

public class B {

	// 필드
	A a1 = new A(true);	// (O)
	A a2 = new A(1);	// (O) : 같은 메소드이름으로 사용가능 = 오버로딩
//	A a3 = new A("s");	// (X) : private - 클래스 내부에서는 사용가능 but B class에서는 사용 불가능.
	
	// 이것이 자바다 263p ~ ---------------------------
	
	public B() {
		A a = new A();

		a.field1 = 1;	// (O)
		a.field2 = 1;	// (O)
//		a.field3 = 1;	// (X)
		
		a.method1();	// (O)
		a.method2();	// (O)
//		a.method3();	// (X)
	}
}
