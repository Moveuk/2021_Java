// 이것이 자바다 _ 신용권 : 197p ~ 
//-----------------------------------------------
// 클래스의 구성 멤버

    // 필드(Field) : 객체의 데이터가 저장되는 곳 - 객체가 존재하는 한 데이터로서 함께 존재함.
//    ex) int fieldName;

    // 생성자(Constructor) : 객체 생성 시 초기화 역할 담당
//    ex) ClassName() {...};

    // 메소드(Method) : 객체의 동작에 해당하는 실행 블록
//    ex) void methodName() {...};


// 예제 코드 시작--------------------------------------------------------
public class Car {

	// 필드 : 전역변수
	String company; // = "현대자동차";
	String model; // = "그랜저";
	String color; // = "검정";
	int maxSpeed; // = 350;
	int speed;

//	public Car() {
//		company = "현대자동차";
//		model = "그랜저";
//		color = "검정";
//		int maxSpeed = 350;
//	}

// 생성자 초기화(초기값 선언)
	public Car() {
		this("BMW", "빨강", 100);
	}

	Car(String model) {
		this(model, "디폴트", 250);
	}

	Car(String model, String color) {
		this(model, color, 250);
	}

	public Car(String model, String color, int maxSpeed) {
		this.model = model;
		this.color = color;
		this.maxSpeed = maxSpeed;
	}
	// 이후 Calc를 통한 매개변수 메소드 수업 진행.




	//----------------------------------------------
	
	
	
	
	// 0603_4교시 : Calc CalcTest 이후 메소드 기능 수업.
	// 메소드(기능)

	void keyTurnOn() {
		System.out.println("시동을 걸다.");
	}

	void keyTurnOff() {
		System.out.println("시동을 끄다.");
	}

	void accel() {
		for (int i = 0; i < 200; i++) {
//			// 속도를 조절해서 보여주고 싶은 경우 : 꼼수임. 자바에서 할필요 없는 기능?일듯
//			for (int k =0; k<1000000000;k++) {
//			} 컴사양이 좋아서 안됨.. ㅋㅋㅋㅋ
			speed = i;
			if (i%10==0 && i%3==0) {
				System.out.println(speed);
			} else if (i==199) {
				System.out.println(speed);
			}
		}
	}

	

}
