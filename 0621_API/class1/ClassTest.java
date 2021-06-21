package class1;

import clone.Car;

public class ClassTest {

	public static void main(String[] args) {
		
		Car car = new Car("마티즈");
		
		Class clazz = car.getClass();	// 클래스의 정보를 알아낼 수 있다.
		
		System.out.println(clazz.getName());
		System.out.println(clazz.getSimpleName());
		System.out.println(clazz.getPackage().getName());
		System.out.println(clazz.getPackageName());
		
		
		// 클래스를 생성하지 않더라도 Car 클래스의 정보를 get할 수 있는 방법.
		try {
			Class Clazz2 = Class.forName("clone.Car");	// 클래스가 있는지 없는지 알고 싶을 때 사용한다.
			// 클래스 생성 이전에 오류를 대비하여 생성함.
			System.out.println(clazz.getName());
			System.out.println(clazz.getSimpleName());
			System.out.println(clazz.getPackage().getName());
			System.out.println(clazz.getPackageName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
