// 이것이 자바다 _ 신용권 : 288p
// Key Word : 상속(Inheritance)
// 상속 : A의 내용이 B에도 필요한 내용이기 때문에 A의 값을 상속받아 사용 가능하다.
// 상속 대상 : 변수와 메소드만 상속이 가능하다 
//			 private, 생성자는 상속이 불가능.
//----------------------------------------------------------------------

package com.phone_inheritance;

public class CellPhone {
	String model;
	String color;

	void powerOn() {
		System.out.println("전원을 켭니다.");
	}

	void powerOff() {
		System.out.println("전원을 끕니다.");
	}

	void bell() {
		System.out.println("벨이 울립니다.");
	}

	void sendVoice(String message) {
		System.out.println("자기:" + message);
	}

	void receiveVoice(String message) {
		System.out.println("상대방:" + message);
	}

	void hangup() {
		System.out.println("전화를 끊습니다.");
	}

}
