package generic_method;

public class Util {
	public static <T> Box<T> boxing(T t) {		// 스태틱하고 제네릭한 메소드를 정의함.
		Box<T> box = new Box<T>();
		box.set(t);
		return box;
	}
}
