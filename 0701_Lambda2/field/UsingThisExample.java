package field;

public class UsingThisExample {
	public static void main(String... args) {
		UsingThis usingThis = new UsingThis();			// �ܺ� Ŭ���� �ν��Ͻ� ����
		UsingThis.Inner inner = usingThis.new Inner();	// ��ø Ŭ���� �ν��Ͻ� ����
		inner.method();
	}
}

