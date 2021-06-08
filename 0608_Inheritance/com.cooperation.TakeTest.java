// 이것이 자바다 _ 신용권 : 288p
// Key Word : 포함 관계, 상속(inheritance)

// 포함 관계 : student가 bus에 있는 인자를 가져와서 사용.
// 상속 : A의 내용이 B에도 필요한 내용이기 때문에 A의 값을 상속받아 사용 가능하다.
// 상속 대상 : 변수와 메소드만 상속이 가능하다 
//			 private, 생성자는 상속이 불가능.
//----------------------------------------------------------------------

package com.cooperation;

public class TakeTest {

	public static void main(String[] args) {
		Student james = new Student("James", 1, 5000);
		Student tomas = new Student("Tomas", 2, 20000);
		
		Bus bus100 = new Bus(100);
		Subway subway2 = new Subway("2호선");
		
		james.takeBus(bus100);
		bus100.showInfo();
		
		tomas.takeSubway(subway2);
		subway2.showInfo();
	}

}
