// 이것이 자바다 _ 신용권 : 248p ~
// Key Word : 패키지(Package), 접근 제한자(Access Modifier), public, protected, default, private, 
//------------------------------------

package access.package2;

import access.package1.A;

public class C {
	// 필드
	A a1 = new A(true);	// (O)
//	A a2 = new A(1);	// (O) : 같은 메소드이름으로 사용가능 = 오버로딩 but 다른 패키지의 C 클래스 사용 불가능.
//	A a3 = new A("s");	// (X) : private - 클래스 내부에서는 사용가능 but B class에서는 사용 불가능.
	
	public C() {
		A a = new A();

		a.field1 = 1;	// (O)
//		a.field2 = 1;	// (X)
//		a.field3 = 1;	// (X)
		
		a.method1();	// (O)
//		a.method2();	// (X)
//		a.method3();	// (X)
	}
	
}
