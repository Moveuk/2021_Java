package generic_method;

public class Util {
	public static <T> Box<T> boxing(T t) {		// ����ƽ�ϰ� ���׸��� �޼ҵ带 ������.
		Box<T> box = new Box<T>();
		box.set(t);
		return box;
	}
}
