package member;

public class MemberTest {

	public static void main(String[] args) {

		Member m1 = new Member("123");	// 아이디가 같은 사람이 존재하진 않는다.
		Member m2 = new Member("123");	// 주민번호와 같이 PK는 하나만 존재해야한다.
		
		// 그러므로 다음과 같이 확인해준다.
		
		if (m1==m2) {	// 동등연산자는 주소값을 비교하는 것이기 때문에 m1,m2 선언한 순간 주소값이 달라진다.(설령 내부 데이터가 같더라도.)
			System.out.println("m1과 m2는 같다.");
		}

		if (m1.equals(m2)) {	// equals는 Object의 메소드로 매개값으로 들어가는 모든 값이 자동 형변환이 일어난다.
			System.out.println("m1과 m2는 같다.");			
		}	// 오버라이딩 전에는 같지 않아서 나오지 않음
		// 이제 equals는 멤버 변수를 직접적으로 비교하는 메소드이다. (값이나옴)
		
		System.out.println("--------hashcode 변경 전-------");
		
		System.out.println(m1.hashCode());
		System.out.println(m2.hashCode());
		// 해시코드가 다르다. 하지만 오버라이드를 통해 각 객체가 같은 해시코드를 같도록 만들 수 있다.
	}
}
