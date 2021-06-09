 # Polymorphism : 다형성.
 <hr/>
 Key Word : 다형성(Polymorphism), 필드의 다형성, 하나의 배열로 객체 관리, 매개 
 <hr/>
### 311p : 7.7.2 필드의 다형성 / field_polymorphsim

  그렇다면 왜 자동 타입 변환이 필요할까?
     
   => 다형성을 구현하는 기술적 방법 때문.
   
   **다형성**이란 동이란 타입을 사용하지만 다양한 결과가 나오는 성질을 말한다.
   
   필드의 타입은 변함이 없지만, 실행 도중에 어떤 객체를 필드로 저장하느냐에 따라 실행 결과가 달라질 수 있다. 이것이 **필드의 다형성**이다.
   
   <hr/>
   
   다음은 field_polymorphism 패키지의 Tire.java 클래스이다.
   
   ```java
   package field_polymorphism;

public class Tire {
	//필드
	public int maxRotation;     			//최대 회전수(최대 수명)
	public int accumulatedRotation;		//누적 회전수
	public String location;       			//타이어의 위치

	//생성자
	public Tire(String location, int maxRotation) {
		this.location = location;               // 필드 값을 초기화 시킴.
		this.maxRotation = maxRotation;         // 필드 값을 초기화 시킴.
	}

	//메소드
	public boolean roll() {
		++accumulatedRotation;		
    // 정상회전(누적<최대)일 경우 실행.
		if(accumulatedRotation<maxRotation) {
			System.out.println(location + " Tire 수명: " + (maxRotation-accumulatedRotation) + "회");
			return true;
		} else { // 펑크(누적=최대)일 경우 실행.
			System.out.println("*** " + location + " Tire 펑크 ***");
			return false;
		}
	}
}
```
   
   Tire 클래스에는 최대 회전수(타이어 수명), 누적 회전수, 타이어의 위치를 표시하는 필드 값과 생성자가 존재하며, 생성자를 통해 해당 필드값을 초기화시켜주고 있다. 또한, roll() 메소드를 구현하여 정상 회전의 경우와 펑크일 경우를 나누어 코드를 짜놓았다.
   
  다음은 Tire를 부품으로 가지는 Car.java 클래스이다. 
   
```java
public class Car {
	//필드 : 자동차는 4개의 타이어를 가진다.
	Tire frontLeftTire = new Tire("앞왼쪽", 6);
	Tire frontRightTire = new Tire("앞오른쪽", 2);
	Tire backLeftTire = new Tire("뒤왼쪽", 3);
	Tire backRightTire = new Tire("뒤오른쪽", 4);
	
	//생성자
	
	//메소드
  // 모든 타이어를 1회 회전시키기 위해 각 Tire 객체의 roll() 메소드를 호출한다. false를 리턴하는 roll()이 있을 경우 stop() 메소드를 호출하고 해당 타이어 번호를 리턴한다.
  
	int run() {
		System.out.println("[자동차가 달립니다.]");
		if(frontLeftTire.roll()==false) { stop(); return 1; };
		if(frontRightTire.roll()==false) { stop(); return 2; };
		if(backLeftTire.roll()==false) { stop(); return 3; };
		if(backRightTire.roll()==false) { stop(); return 4; };
		return 0;
	}
	
  // 펑크났을 경우 실행.
	void stop() {
		System.out.println("[자동차가 멈춥니다.]");
	}
}
```   
   
   코드를 보고 이해하면 각 타이어의 객체(인스턴스)를 만들어주고 각 타이어가 run되어 tire.roll 될 수 있도록(차가 굴러가 타이어가 돌 수 있도록) 코드가 작성되어 있다.
   
   stop은 펑크가 났을 경우 실행되게 된다.
   
   % 문제점 : run 메소드는 return하는 순간 빠져나오므로 타이어가 동시에 2곳이 파손될 경우 첫번째 타이어만 인식하며 실제 이런 구성을 원한다면 이런 방식은 기피해야 한다.
   
      
   
   다음은 Tire의 자식 클래스인 HankookTire.javar과 KumhoTire.java 클래스를 보자.
   
```java
public class HankookTire extends Tire {
	// 필드
	// 생성자
	public HankookTire(String location, int maxRotation) {
		super(location, maxRotation);
	}

	// 메소드
	@Override
	public boolean roll() {
		++accumulatedRotation;
		if (accumulatedRotation < maxRotation) {
			System.out.println(location + "HankookTire 수명: " + (maxRotation - accumulatedRotation) + "회");
			return true;
		} else {
			System.out.println("*** " + location + " HankookTire 펑크 ***");
			return false;
		}
	}
}
```
  
  KumhoTire.java 클래스는 공간을 위하여 생략되었고, 구성은 똑같으며 클래스명과 출력되는 내용만 바꾸어주면 된다.
  
  크게 다르지 않다 다만 일반 타이어와 구분하기 위하여 roll() 메소드에서 출력시 타이어명을 같이 출력해줄 수 있도록 변경하였다.
  
  중요한 점은 생성자의 매개 변수가 같음을 기억하자.
  
     
  다음은 CarExample.java 클래스이다.

```java
public class CarExample {
	public static void main(String[] args) {
		Car car = new Car();                  // Car 객체 생성.
		
		for(int i=1; i<=5; i++) {             // 타이어 변경 여부를 보기 위한 단순 반복문.(5회 작동)
			int problemLocation = car.run();
			switch(problemLocation) {           // 각 타이어별로 번호를 매겨 고장 여부를 확인 가능.
				case 1:
					System.out.println("앞왼쪽 HankookTire로 교체");
					car.frontLeftTire = new HankookTire("앞왼쪽", 15);
					break;
				case 2:
					System.out.println("앞오른쪽 KumhoTire로 교체");
					car.frontRightTire = new KumhoTire("앞오른쪽", 13);	
					break;
				case 3:
					System.out.println("뒤왼쪽 HankookTire로 교체");
					car.backLeftTire = new HankookTire("뒤왼쪽", 14);	
					break;
				case 4:
					System.out.println("뒤오른쪽 KumhoTire로 교체");
					car.backRightTire = new KumhoTire("뒤오른쪽", 17);		
					break;
			}
			System.out.println("----------------------------------------");
		}
	}
}
```  
  
  주석의 내용 대로 CarExample 클래스에서는 Car 객체를 생성 후 타이어를 roll하기 위한 준비과정을 끝마쳤다.
  
  실행해보면 Console창에 다음과 같은 내용이 출력되며 펑크가 나게되면 타이어는 Switch문에서 설정된 타이어에 맞추어 변경된 후 다시 실행된다.
  
   ![image](https://user-images.githubusercontent.com/84966961/121355521-eaec4080-c96a-11eb-9c7e-1756c7793f4d.png)
   
  <hr/>
   
  ### 318p : 하나의 배열로 객체 관리
  
   이전 예제에서는 Tire 객체를 4개의 필드로 각각 저장했다. 하지만 동일한 타입의 값들을 배열로 관리하는 것이 유리하기 때문에 Tire 객체들을 배열로 바꾸어 보려고 한다.
   
```java
	Tire[] tires = {
			new Tire("앞왼쪽", 6),
			new Tire("앞오른쪽", 2),
			new Tire("뒤왼쪽", 3),
			new Tire("뒤오른쪽", 4)
	};
  ```
    
  각각의 객체를 따로 설정할 필요 없이 배열로 변경 가능하며 다음 할 일은 연관된 모든 코드가 배열의 값으로 받을 수 있도록 바꾸는 것이다.
  
  **Car run method**
  
  ```java
    int run() {
		  System.out.println("[자동차가 달립니다.]");
      int i = 0;
		  for (Tire t : tires) {
			  ++i;
			  if(t.roll()==false) { stop(); return i;};
		}
		 return 0;
  }
```
  if문 4줄의 코드가 for문 하나로 변경된다.
     
  **CarExample 실행 클래스 배열 변경**
  ```java
  for(int i=1; i<=5; i++) {
			int problemLocation = car.run();
			if(problemLocation != 0) {
			  	System.out.println(car.tires[problemLocation-1].location + " HankookTire로 교체");
			  	car.tires[problemLocation-1] = new HankookTire(car.tires[problemLocation-1].location, 15);
//				}
```
    
  스위치 문으로 길게 적어야 했던 것들이 짧게 바뀐다. 다만, 현재 코드는 모든 타이어를 한국 타이어로 사용하고 있다.
  
  <hr/>
     
  ### 321p : 7.7.4 매개 변수의 다형성 
  
   자동 타입 변환은 필드의 값을 대입할 때에도 발생하지만, 주로 메소드를 호출할 때 많이 발생한다.
  
  ![image](https://user-images.githubusercontent.com/84966961/121358809-d9f0fe80-c96d-11eb-902d-c4e672d1eafa.png)
    
 **Driver 클래스**     
       
```java
public class Driver {
	public void drive(Vehicle vehicle) {
		vehicle.run();	}}
```
   
 **DriverExample 클래스**   
    
```java
public class DriverExample {
	public static void main(String[] args) {
		Driver driver = new Driver();
		
		Bus bus = new Bus();
		Taxi taxi = new Taxi();
		
		driver.drive(bus);
		driver.drive(taxi);	}}
```

  위의 코드를 보면 bus와 taxi는 타입이 각각 bus와 taxi이다. 하지만 driver클래스의 drive 메소드는 Vehicle을 매개 변수 타입으로 받는다.
  이 것처럼 상속 관계에 있는 자식 클래스들은 부모 클래스 타입으로 자동 형변환이 됨으로 매개 변수 자리에도 들어갈 수 있다.
  Bus와 Taxi 클래스는 Vehicle을 상속받아 다음과 같이 오버라이딩을 하였으며, DriverExample이 실행될 경우 각각의 매개 변수 마다 다른 결과를 불러오게 된다.   
     
 **Bus 클래스**   
  
```java
public class Bus extends Vehicle {
	@Override
	public void run() {
		System.out.println("버스가 달립니다.");	}}
```

 다음은 Train과 Airplane을 추가하여 아래의 모식도처럼 구성한 코드들이다.   
    
<img src = "https://user-images.githubusercontent.com/84966961/121296279-5b736d00-c92b-11eb-9fb0-fbf88d899409.png" width="30%">   
   
 **Train 클래스**   

```java
public class Train extends Vehicle {
	@Override
	public void run() {
		System.out.println("기차가 달립니다.");	}}
```
   
 **Airplane 클래스**   
```java
public class Airplane extends Vehicle {
	@Override
	public void run() {
		System.out.println("비행기가 이동합니다.");	}}
```

<hr/>
