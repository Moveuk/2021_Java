package member;

public class Member {
	
	public String id;

	public Member(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
// 멤버 매개값 id를 직접적으로 비교하는 것으로 메소드를 재정의한다.
		if (obj instanceof Member) {
			
			Member other = (Member)obj;
			
			return this.id.equals(other.id);
			
		}
			return false;	// 반드시 if 구문 외부에 return을 주어야 프로그램에서 타입 값을 인식한다.
	}

	@Override
	public int hashCode() {
		return id.hashCode();	// id값의 해시코드로 통일 시켜버린다.
	}

}


//int a = 10;
//
//int b = 10;
//
//b = a;