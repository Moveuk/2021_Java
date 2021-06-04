// 이것이 자바다 _ 신용권 : 248p ~
// Key Word : 패키지(Package), 접근 제한자(Access Modifier), public, protected, default, private, 
//------------------------------------

package access.package1;

public class A {

	// 필드
	A a1 = new A(true);	// (O)
	A a2 = new A(1);	// (O) : 같은 메소드이름으로 사용가능 = 오버로딩
	A a3 = new A("s");	// (O) : private - 클래스 내부에서는 사용가능.
	
	public A(boolean b) {
		
	}
	A(int b) {
		
	}
	private A(String s) {
		
	}
	
	// 이것이 자바다 263p ~ ---------------------------
	
	//필드
	public int field1;
	int field2;
	private int field3;
	
	//생성자
	public A() {
		field1 = 1;	// (O)
		field2 = 1;	// (O)
		field3 = 1;	// (O)
		
		method1();	// (O)
		method2();	// (O)
		method3();	// (O)
	}
	
	public void method1() {}
	void method2() {}
	private void method3() {}
	
}
