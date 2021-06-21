package wrapper;

public class WrapperExample {

	public static void main(String[] args) {
		// wrapper
		// 기본형을 참조형으로 형변환한다.(오토박싱)
		
//		Integer i = new Integer(100);	// 100을 가지고 있는 것이 아니라 힙영역의 주소값을 가지고 있는 것이다.
//		Double d = new Double(20.3);
//		
//		System.out.println(i.toString());
//		System.out.println(d);
		
		int x = 10;
		Integer i = x;
		
		System.out.println(i);
		
		Integer i2 = 30; 		// 바로 변수를 넣을 수 있다.
		int y = i2;				// 심지어 참조를 기본 타입에 넣어도 된다.
		
		Object obj = y;			// new Integer(y); 객체가 생성되서 들어감.
		
		System.out.println(i + i2);	// int + Integer 계산 가능
		
		int pi =Integer.parseInt("100");
		double di = Double.parseDouble("23.1");
		

	}

}
