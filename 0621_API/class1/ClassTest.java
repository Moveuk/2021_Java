package class1;

import clone.Car;

public class ClassTest {

	public static void main(String[] args) {
		
		Car car = new Car("��Ƽ��");
		
		Class clazz = car.getClass();	// Ŭ������ ������ �˾Ƴ� �� �ִ�.
		
		System.out.println(clazz.getName());
		System.out.println(clazz.getSimpleName());
		System.out.println(clazz.getPackage().getName());
		System.out.println(clazz.getPackageName());
		
		
		// Ŭ������ �������� �ʴ��� Car Ŭ������ ������ get�� �� �ִ� ���.
		try {
			Class Clazz2 = Class.forName("clone.Car");	// Ŭ������ �ִ��� ������ �˰� ���� �� ����Ѵ�.
			// Ŭ���� ���� ������ ������ ����Ͽ� ������.
			System.out.println(clazz.getName());
			System.out.println(clazz.getSimpleName());
			System.out.println(clazz.getPackage().getName());
			System.out.println(clazz.getPackageName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
