
   # 343p : 8 인터페이스(Interface)   
     
   <hr/>   
      
## 344p : 8.1 인터페이스의 역할   
   
 자바에서 인터페이스(Interface)는 **객체의 사용 방법을 정의한 타입**이다. -> **객체 사용 설명서**   
 자바 8의 경우 람다식은 함수적 인터페이스의 구현 객체를 생성하기 때문에 중요하다.   
    
 인터페이스(Interface)는 개발 코드와 객체가 서로 통신하는 접점 역할을 한다. 개발 코드가 인터페이스의 메소드를 호출하면 인터페이스는 객체의 메소드를 호출시킨다. 그렇기 때문에 개발 코드는 객체의 내부 구조를 알 필요가 없고 인터페이스의 메소드만 알고 있으면 된다.
    
 ![image](https://user-images.githubusercontent.com/84966961/121457753-24609280-c9e3-11eb-9cf1-bb34d429ffb5.png)
   
 그렇다면 왜? 굳이 직접 호출하지 않고 인터페이스를 통해 호출하는가?    
    
 => 개발 코드를 수정하지 않고, 사용하는 객체를 변경할 수 있도록 하기 위해서이다. 위의 그림처럼 인터페이스는 여러 객체와 함께 상호 작용하므로 어떤 객체를 사용하느냐에 따라서 실행 내용과 리턴값이 다를 수 있다. 따라서 개발 코드 측면에서는 **코드 변경 없이 실행 내용과 리턴값을 다양화할 수 있다는 장점**을 가지게 된다.   
    
 <hr/> 
## 344p : 8.2 인터페이스 선언   
**인터페이스 선언**
![image](https://user-images.githubusercontent.com/84966961/121458217-f29bfb80-c9e3-11eb-817d-d5df614d9601.png)   
**인터페이스 생성 방법**   
![image](https://user-images.githubusercontent.com/84966961/121455527-9800a080-c9df-11eb-8a19-12e8fd17f903.png)
    
**인터페이스의 특징**
```java
public interface Phone {
	// 상수 
	// 추상메소드
	// default메소드
	// static메소드
	// ==========================
	// 인터페이스 특징
	// 인터페이스 자체 생성불가(익명으로 할시는 생성 가능함.)
	// 상속만 가능(구현)
	// 멤버변수,메소드는 정의불가
	// 자식클래스에서 반드시 오버라이딩
}
```

 ### 347p ~ : 추상, 디폴트, 정적 메소드 선언
 
 **RemoteControl 인터페이스 작성 **
 ```java
 public interface RemoteControl {
	public static final int MAX_VOLUME = 10; //public static final 생략가능(의미는 존재)
	int MIN_VOLUME = 0;
	
	public abstract void turnOn();
	void turnOff();
	void setVolume(int volume);
	
	// 348p : 8.2.4 디폴트 메소드 선언
	default void setMute(boolean mute) { // public 이 자동으로 컴파일 과정에서 붙음.
		if(mute) {
			System.out.println("무음처리");
		} else {
			System.out.println("무음해제");
		}
	}
	
	// 349p : 8.2.5 정적 메소드 선언
	static void ChangeBattert() {  // public 이 자동으로 컴파일 과정에서 붙음.
		System.out.println("건전지 교체");
	}
}
```

   <hr/>   
      
### 351p : 8.3.1 구현 클래스
   
 **인터페이스의 상속**   
  다음 그림과 같이 상속된 인터페이스를 생성할 수 있으며 클래스 상속과 구분하기 위하여 코드상 'implements RemoteControl'라고 표시한다.
 
![image](https://user-images.githubusercontent.com/84966961/121456345-e2cee800-c9e0-11eb-92eb-a6e4db80777e.png)
```java
public class Television implements RemoteControl {
	@Override
	public void turnOn() {
	}
	@Override
	public void turnOff() {
	}
	@Override
	public void setVolume(int volume) {
	}
```   
   
 **Television, Audio Class**

![image](https://user-images.githubusercontent.com/84966961/121457035-faf33700-c9e1-11eb-880a-f58434f652d6.png)
   
 위의 코드를 보면 인터페이스로부터 상속된 필드를 이용하여 제어하고 있음을 알 수 있다.
 
 
    <hr/>   
      
### 354p : 8.3.2 익명 구현 객체

 일회성의 구현 객체를 만들기 위해 소스 파일을 만들고 클래스를 선언하는 것은 비효율적이므로 익명으로 객체를 생성하여 소스 파일을 생성하지 않고도 사용할 수 있다.    
 **주로 이벤트 처리시 사용을 하게 된다.(handler)** but, 자바는 GUI가 무거워서 쓰지 않는다. 웹에서 주로 사용하게 되면 JS를 통하여 반응하게 할 수 있으므로 굳이 사용하지 않는다.    
    
  **익명 구현 객체 = 임시 작업 스레드 생성**   
   
```java
	RemoteControl rc = new RemoteControl() { //익명 객체를 사용하여 인터페이스를 생성하였음.
		@Override
		public void turnOn() {
			System.out.println("익명객체 켜기");
		}
		@Override
		public void turnOff() {
		}
		@Override
		public void setVolume(int volume) {
		}
	};
		
		rc.turnOn(); // 익명객체 실행문
```

 Television이나 Audio처럼 똑같이 상속 받은 것으로 치지만 클래스 내부에서 사용되고 사라지고 오버라이딩을 통해 다음과 같은 실행 결과를 출력하게 된다.
 
 **실행화면**   
 ![image](https://user-images.githubusercontent.com/84966961/121459745-c59d1800-c9e6-11eb-9e50-bbc50fa5ad86.png)   
   
    <hr/>   
      
### 356p : 8.3.3 다중 인터페이스 구현 클래스

![image](https://user-images.githubusercontent.com/84966961/121459972-288eaf00-c9e7-11eb-876f-a7848a01ad74.png)
   
 그림에서 객체 부분이 우리가 작성하고자 하는 클래스이며 우리는 클래스와 인터페이스를 사용하고 싶기에 다중으로 인터페이스를 불러온다.   
 또한, -able이 붙는 이름은 인터페이스라고 암묵적으로 정의한다.   
 **다중 인터페이스 상속 방법**
```java
 public class 구현클래스명 implements 인터페이스A, 인터페이스B {
 	// 인터페이스 A에 선언된 추상 메소드의 실체 메소드 선언
 	// 인터페이스 B에 선언된 추상 메소드의 실체 메소드 선언
}
```

 다음은 SmartTelevision.java 클래스에 다중 추상 인터페이스를 상속한 예제이다.

```java
public class SmartTelevision implements RemoteControl, Searchable {
	private int volume;
	
	@Override
	public void turnOn() {
		System.out.println("TV를 켭니다.");
	}

	@Override
	public void turnOff() {
		System.out.println("TV를 끕니다.");
	}

	@Override
	public void setVolume(int volume) {
		if(volume>RemoteControl.MAX_VOLUME) {
			this.volume = RemoteControl.MAX_VOLUME;
		} else if(volume<RemoteControl.MIN_VOLUME) {
			this.volume = RemoteControl.MIN_VOLUME;
		} else {
			this.volume = volume;
		}
		System.out.println("현재 TV 볼륨: " + this.volume);
	}

	public void search(String url) {
		System.out.println(url + "을 검색합니다.");
	}
}
```
   
 부모가 2명이므로 부모 타입 2개모두 사용할 수 있으며 다음과 같이 Searchable 타입으로 사용할 수 있다.   
   
 **RemoteControlTest 클래스 에서 실행**   
```java
public class RemoteControlTest {
		Searchable sa = new SmartTelevision(); // Searchable 타입으로 생성
		sa.search("동물");
	}
}
```   
   
 잘 실행 되는 것을 볼 수 있다.
 
    <hr/>   
      
### 360p : 8.4.2 디폴트 메소드 사용

 디폴트 메소드란 일반 클래스의 상속처럼 상속시 자식 클래스에서 굳이 따로 정의해주지 않아도 되는 메소드를 말한다. 즉, 공통적인 메소드에 디폴트 메소드를 선언하면 된다.   
   
**RemoteControl 클래스에 선언된 디폴트 메소드**   
```java
public interface RemoteControl {
	...
	
	// 348p : 8.2.4 디폴트 메소드 선언
	default void setMute(boolean mute) { // public 이 자동으로 컴파일 과정에서 붙음.
		if(mute) {
			System.out.println("무음처리");
		} else {
			System.out.println("무음해제");
		}
	}
	...
}
```
**Audio 클래스에 디폴트 메소드 재선언**
```java
public class Audio implements RemoteControl {
	//필드
	private int volume;
	private boolean mute;
	
	...
	
	@Override
	public void setMute(boolean mute) {
		this.mute = mute;
		if(mute) {
			System.out.println("Audio 무음 처리합니다.");
		} else {
			System.out.println("Audio 무음 해제합니다.");
		}
	}
}
```

RemoteControl 클래스에 선언된 디폴트 메소드를 상속받은 후 다른 클래스에서 사용하려면 디폴트 메소드에 대한 재정의가 필요하다. 
따라서 필요한 mute 필드를 선언해주고


