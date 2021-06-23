 # Polymorphism : 다형성.
 <hr/>
 Key Word : 다형성(Polymorphism), 필드의 다형성, 하나의 배열로 객체 관리, 매개 
 <hr/>
### 311p : 7.7.2 필드의 다형성 / field_polymorphsim

  그렇다면 왜 자동 타입 변환이 필요할까?
     
   => 다형성을 구현하는 기술적 방법 때문.
   
   **다형성**이란 동일한 타입을 사용하지만 다양한 결과가 나오는 성질을 말한다.
   
   필드의 타입은 변함이 없지만, 실행 도중에 어떤 객체를 필드로 저장하느냐에 따라 실행 결과가 달라질 수 있다. 이것이 **필드의 다형성**이다.
   
   ex) Tire frontLeftTire = new Hankooktire();   
   
 타이어 객체에 한국타이어 객체가 들어가서 이 객체를 이용한 메소드를 만들어 두고 다른 곳에서 호출하여 사용함.    
 자세히 뜯어보면 Tire는 interface이며 Hankooktire()는 Tire를 Implements한 구현클래스이다.   
 따라서 금호 한국 모두 Tire의 구성으로 되있으니 객체 생성 후 사용할 수 있는 것이다. 
    
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

  ### 324p : 7.7.5 강제 타입 변환(Casting)
  
  ```
  자식 클래스 변수 = (자식클래스) <U>부모 클래스 타입;<U/>
  				자식 타입이 부모 타입으로 변환된 상태.
  like
  
  Class Parent; Class Child;
  
  public class ChildExample {
	public static void main(String[] args) {
		Parent parent = new Child();
		parent.field1 = "data1";
		parent.method1();
		parent.method2();
		/*
		parent.field2 = "data2";  //(불가능)
		parent.method3();         //(불가능)
		*/
		
		Child child = (Child) parent;
		child.field2 = "yyy";  //(가능)
		child.method3();     //(가능)
	}
}
  ```

 위 코드 내용 처럼 Chlid 를 Parent 타입으로 변환했을때는 Child의 필드와 메소드를 사용하지 못하지만, 다시 Child 타입의 변수에 선언하게 되면 Child의 필드와 메소드를 다시 사용할 수 있게 된다.   

   
<hr/>
   2021.06.10 이어서 수업..   
      
 ### 326p : 7.7.6 객체 타입 확인(instanceof)

 앞서 말한 강제 타입 변환은 **자식 타입이 부모타입으로 변환되어 있는 상태**에서만 가능하기 때문에 다음과같이 부모 타입의 변수가 부모 객체를 참조할 경우 자식 타입으로 변환할 수 없다.
 
 ```java
  Parent parent = new Parent();
  Child child = (Child) parent;   // 강제 타입 변환을 할 수 없다.(위에 부모가 자식으로 안바뀌었기 때문.)
 ```
    
 이런 경우 부모 변수가 참조하는 객체가 부모 객체인지 자식 객체인지 확인할 필요가 있다. 이 때 사용하는 것이 instanceof 이다.   
 
 instanceof는 **어떤 객체가 어떤 클래스의 인스턴스인지 확인**하기 위하여 사용하는 것이다.   
 
 사용법은 다음과 같다.   
 ```java
 boolean result = 좌향(객체) instanceof 우향(타입)
 ```
 (해석) : 좌향(객체) 가 우향의 타입인지(true) 아닌지(false)를 result에 반환하라.
 
 위 코드의 타입처럼 instanceof는 boolean 타입의 true or false로 반환되며 메소드 내에서 강제 타입 변환이 필요할 경우 반드시 매개값이 어떤 객체인지 instanceof 연산자로 확인하고 안전하게 강제 타입 변환을 해야 한다.
   
 만약 타입을 확인 하지 않고 강제 타입 변환을 시도하면 ClassCastException 예외가 발생할 수 있으며 다음 예제를 통해 이해해보자.   
   
 **Car.java class 작성**   	: Car 에는 아무런 필드, 메소드가 없음.
 **FireCar.java class 작성**   	
 **Ambulance.java class 작성**   	: Firecar와 Ambulance에는 다음과 같은 메소드가 들어있음.
```java
public class FireCar extends Car {
	void fire() {
		System.out.println("화재진압~~");
	}
}
```
 **CarTest.java class**
```java
package instance;

public class CarTest {

	public static void main(String[] args) {
		FireCar fc = new FireCar();
		Ambulance ab = new Ambulance();
		
		work(fc); // work의 매개변수는 Car 타입, fc는 FireCar 타입이지만 자동 타입 변환이 일어남.
		work(ab); // work의 매개변수는 Car 타입, fc는 Ambulance 타입이지만 자동 타입 변환이 일어남.
	}
	
	public static void work(Car c) {
		if(c instanceof FireCar) { // c의 타입이 FireCar인가?
			FireCar fc = (FireCar) c;
			fc.fire();
		} else if (c instanceof Ambulance) { // c의 타입이 Ambulance인가?
			Ambulance ab = (Ambulance)c;
			ab.siren();
		}
	}
}
```

 위 코드 처럼 어떤 메소드에 들어가는 매개 변수가 부모 자식 간의 상속 관계에서 다양해질 수 있으므로 매개 변수를 받을 때 instanceof 연산자를 통해 구분할 수 있다.
 
 또한, 굳이 강제 타입 변환(Casting)하는 이유는 Car에는 없는 메소드(fire,siren)를 사용하기 위함이다. 
 
 실행해보면 다음과 같이 Car 타입으로 넣었지만 Car에는 없는 메소드가 출력된다.
 
 ![image](https://user-images.githubusercontent.com/84966961/121449219-56b6c380-c9d4-11eb-947f-a5aaa85020fa.png)
   
    
    <hr/>

 ## 329p : 7.8 추상클래스(Astract)   
 <hr/>
 ### 329p : 7.8.1 추상클래스의 개념(instanceof) 
 
 추상화를 하게 되면 메소드의 경우 특히 상속받는 자식 클래스에서 반드시 오버라이딩을 해주어야 하므로 전체적인 기능 구성에서 필요한 부분들을 강제할 수 있다는 장점이 있다.
 즉, 추상화는 코드 작성 혹은 기능 구현에 있어서 청사진을 제공하며 효율을 올려주는 역할을 할 수 있다.
 
**추상 클래스의 생성 방법.**   
 아래 그림처럼 클래스 생성시 Abstract 버튼을 클릭하면 된다. 혹은 아래 코드처럼 선언해주면 된다.

![image](https://user-images.githubusercontent.com/84966961/121449075-ff185800-c9d3-11eb-878e-d5a8aa1f0c6b.png)
```java
public abstract class Phone {
	// 추상클래스의 특징
	// 1. 생성이 불가능하다.
	// 2. 상속만 가능하다.
	// 3. 추상 메소드를 포함한다. (선언부만 정의한다.)
	// 4. 자식에서 추상 메소드를 반드시 오버라이딩 해야한다.
	// 5. 일반 클래스와 동일하게 멤버 변수와 메소드를 정의 할 수 있다.
}
```



### 334p : 7.8.4 추상 메소드와 오버라이딩

모식도 그림

 추상 클래스에 추상 메소드를 만든 후 상속받는 자식 클래스를 생성하면 다음 그림과 같이 자동으로 Override할 sound() 메소드가 생성된다.
![image](https://user-images.githubusercontent.com/84966961/121449920-b366ae00-c9d5-11eb-9e9a-48dd3c79b95b.png)
   
 다음은 AnimalTest.java 코드를 통해 개념을 잡아보자.
 
```java
package abstract1;

public class AnimalTest {
	public static void main(String[] args) {
		Dog dog = new Dog("강아지");
		Cat cat = new Cat("고양이");
		dog.sound();
		cat.sound();
		System.out.println("-----");
		
		//변수의 자동 타입 변환
		Animal animal = null;
		animal = new Dog("강아지");      // 부모 타입으로 매개 변수를 잡고
		animal.sound();			// animal에 내용이 정의되지 않은 메소드를 넣더라도 내용물이 잘나옴.
		animal = new Cat("고양이");
		animal.sound();
		System.out.println("-----");
		
		//매개변수의 자동 타입 변환
		animalSound(new Dog("강아지")); // Animal 타입으로 각 매개 변수가 바뀜.
		animalSound(new Cat("고양이"));
	}
	
	public static void animalSound(Animal animal) {
		animal.sound();
	}
}
```

  교재 310p ~ 311p의 내용처럼 자동 타입 변환에 의해 추상화된 객체의 메소드 또한 쉽게 사용가능하다.
  
  일반 클래스의 상속과 크게 기능적으로는 다르지 않으므로 결국 초반에 설명한 것처럼 추상화의 핵심은 **전체 구현의 청사진을 제공**하는 역할을 한다는 점이다.

 <hr/>
   
 다음 스타크래프트 예제를 통해 상속과 추상화에 대해 이해해보자.
 
![image](https://user-images.githubusercontent.com/84966961/121452072-bf546f00-c9d9-11eb-8613-4923763dc5c2.png)

 Marine, Dropship, Tank에 모두 같은 필드와 메소드들이 정의 되어있다.    
 이런 경우 **상속**을 사용하여 코드의 중복을 줄일 수 있다.(코드의 최소화)
   
 Unit이라는 클래스를 만들어 공통 인자들을 상속 시켜주도록 만들면 된다.

![image](https://user-images.githubusercontent.com/84966961/121453501-3ee33d80-c9dc-11eb-89fb-01636745130a.png)

 문제점은 지상 유닛과 공중 유닛의 이동하는 부분이 다르다는 점이다.   
 지상 유닛은 한정된 x,y 값에서 움직이며 언덕을 못넘어가지만 공중 유닛은 x,y 값에 상관없이 이동이 가능하다.
   
 이 경우에 사용할 수 있는 것이 **추상화**이다. 

 stop은 같은 기능을 사용하지만 move는 유닛마다 다른 기능을 구현해 주어야 한다.

 move를 추상화하면 다음과 같이 오류가 생기며 override 로 재정의해주면 된다.

![image](https://user-images.githubusercontent.com/84966961/121454137-3fc89f00-c9dd-11eb-9bc8-95408ebd3bea.png)
   
 다음 코드 작성처럼 Unit이 추상화가 되어 있다면 상속된 자식 클래스의 내용과 기능을 강제할 수 있다.
   
![image](https://user-images.githubusercontent.com/84966961/121454347-9504b080-c9dd-11eb-9c7c-029f3488b95e.png)
   
   




