// 이것이 자바다 _ 신용권 : 303p
// Key Word : protected, 접근제어자(Access Modifier)
// protected : 내부 패키지 혹은 자식 클래스 내부에서만 사용 가능하다는 뜻의 접근 제어자.
//----------------------------------------------------------------------

package protected_modi.package2;

import protected_modi.package1.Apack;

public class Bpack extends Apack{
	
	// protected 는 다른 패키지에서는 사용 불가능. 오직 상속 받아야만 가능.
//	Apack ap = new Apack();
//	ap.y = 100;
	// 사용 못함.
	
	void printTest() {
//		x = 10; // private 사용 불가
		y = 20;
		z = 30;
		
//		System.out.println(x);
		System.out.println(y);
		System.out.println(z);
	}

}
