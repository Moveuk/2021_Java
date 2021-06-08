// 이것이 자바다 _ 신용권 : 288p
// Key Word : 상속(Inheritance)
// 상속 : A의 내용이 B에도 필요한 내용이기 때문에 A의 값을 상속받아 사용 가능하다.
// 상속 대상 : 변수와 메소드만 상속이 가능하다 
//			 private, 생성자는 상속이 불가능.
//----------------------------------------------------------------------

package com.phone_inheritance;

public class DmbCellPhone extends CellPhone {
	//필드
	int channel;
	
	//생성자
	DmbCellPhone(String model, String color, int channel) {
		//자식 클래스에서의 첫줄 : super()                                                                                          
		// super는 부모의 기본 생성자 호출이다.
		// 부모가 기본 생성자가 없고, CellPhone(매개값, ...)처럼 매개 변수가 있는 생성자만 있다면 
		// 자식에서 반드시 명시적으로 super(매개값, ...)를 호출해줘야 한다.
//		super(); // 부모에 기본 생성자만 되어있을 경우 생략 가능.
		this.model = model;
		this.color = color;
		this.channel = channel;
	}

	//메소드
	void turnOnDmb() {
		System.out.println("채널 " + channel + "번 DMB 방송 수신을 시작합니다.");
	}	
	void changeChannelDmb(int channel) {
		this.channel = channel;
		System.out.println("채널 " + channel + "번으로 바꿉니다.");
	}
	void turnOffDmb() {
		System.out.println("DMB 방송 수신을 멈춥니다.");
	}	
	void hangUp() {
		
	}
}

