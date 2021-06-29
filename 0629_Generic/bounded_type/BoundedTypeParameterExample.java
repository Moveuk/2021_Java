package bounded_type;

public class BoundedTypeParameterExample {
	public static void main(String[] args) {
		//String str = Util.compare("a", "b"); (x)
		// Number 하위 타입만 사용가능하기 때문에 String은 사용 불가능.
		
		// 뒤가 크면 -1 같으면 0 앞이크면 1
		int result1 = Util.compare(10, 20);
		System.out.println(result1);
		
		int result2 = Util.compare(4.5, 3);
		System.out.println(result2);
	}
}
