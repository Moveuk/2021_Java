package field;

public class UsingThisExample {
	public static void main(String... args) {
		UsingThis usingThis = new UsingThis();			// 외부 클래스 인스턴스 생성
		UsingThis.Inner inner = usingThis.new Inner();	// 중첩 클래스 인스턴스 생성
		inner.method();
	}
}

