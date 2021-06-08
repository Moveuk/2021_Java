// 이것이 자바다 _ 신용권 : 303p
// Key Word : protected, 접근제어자(Access Modifier)
// protected : 내부 패키지 혹은 자식 클래스 내부에서만 사용 가능하다는 뜻의 접근 제어자.
//----------------------------------------------------------------------

package protected_modi.package2;

public class ProtectedTest {

	public static void main(String[] args) {
		Bpack bp = new Bpack();
//		bp.y = 100;
//		상속 받은 것이지만 외부 클래스에선 사용 불가능. 오직 내부에서만
		bp.printTest();
		// 잘 나옴. 

	}

}
