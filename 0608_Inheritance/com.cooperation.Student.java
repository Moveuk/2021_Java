// 이것이 자바다 _ 신용권 : 288p
// Key Word : 포함 관계, 상속(inheritance)

// 포함 관계 : student가 bus에 있는 인자를 가져와서 사용.
// 상속 : A의 내용이 B에도 필요한 내용이기 때문에 A의 값을 상속받아 사용 가능하다.
// 상속 대상 : 변수와 메소드만 상속이 가능하다 
//			 private, 생성자는 상속이 불가능.
//----------------------------------------------------------------------

package com.cooperation;

public class Student {
	String studentName;
	int grade;
	int money;

	public Student(String stuedentName, int grade, int money) {
		super();
		this.studentName = stuedentName;
		this.grade = grade;
		this.money = money;
	}

	void takeBus(Bus bus) {
		int charge = 1500;
		bus.take(charge);
		this.money -= charge;
	}

	void takeSubway(Subway subway) {
		int charge = 1250;
		subway.take(charge);
		this.money -= charge;
	}

	void showInfo() {
		System.out.println(studentName + "님의 남은 금액은" + money + "원");
	}

}
