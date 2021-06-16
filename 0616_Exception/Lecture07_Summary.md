# 익명 객체(Anonymous)
Key Word : 익명 객체(Anonymous)     
   
<hr/>
   

### 교재 403p : 지난 수업 이어서 설명.   
   
 기능을 더하고 싶을 때는 기본 기능 클래스를 수정하는 것이 아니라 인터페이스를 통해 기능 추가를 하는 것이 결합도를 감소시키는 측면에서 이득이다. 기존에 만들어 두었던 기능에 `ChatingListner`라는 기능을 추가하여 코드를 실행시켜 보았다.

```java
package nested.interface1;

public class ButtonExample {

	public static void main(String[] args) {
		Button btn = new Button();
		
		MessageListener m = new MessageListener();
		
		btn.setOnClickListener(new CallListener());		// listener에 원하는 구현 클래스를 넣는 기능.
		btn.touch();									// 해당 이벤트 리스너에 있는 onClick 메소드 사용.
		
		btn.setOnClickListener(m);	// 이렇게 변수 만들어서 해도됨.
		btn.touch();
		
		btn.setOnClickListener(new ChatingListener());  // 채팅을 추가하서 사용하면 결합도가 낮아짐. 모듈화
		btn.touch();
		

	}

}
```

<br/><br/>
<hr/>

## 교재 404p : 익명 객체   
   
 익명 객체는 기존에는 변수에 저장하여 주소값을 따라가서 사용하는 방식이었지만 변수 이름없이 생성하여 추후 다시 찾을 수 없는 객체를 생성하여 사용하는 것이다.   
   
 기존에 인터페이스는 인스턴스화하여 생성할 수 없었다. 하지만 인터페이스를 다음 코드와 같이 이용하여 이름없는 객체를 만들어 사용하는 것이 익명 객체이다.   
   
```java
new Interface() {
 ...
}
```

<br/><br/>
<hr/>

### 교재 411p :    
   
 구현 클래스 내부의 메소드는 `public`을 붙여 사용해야 한다.(교재 345p : 인터페이스의 메소드는 모두 퍼블릭이 생략되어 있다.)   
   
<br/><br/>
<hr/>

### 교재 418 : 5번 문제   
   
**Vehicle.java**
```java
public interface Vehicle {
	public void run();
}

```
**Anonymous.java**
```java
public class Anonymous {
	Vehicle field = new Vehicle() {
		
		@Override
		public void run() {
		System.out.println("자전거가 달립니다.");	
		}
	};
	
	void method1() {
		Vehicle localVar = new Vehicle() {
			
			@Override
			public void run() {
			System.out.println("승용차가 달립니다.");	
			}
		};
		localVar.run();
	}
	
	void method2(Vehicle v) {
		v.run();
	}
}

```
**AnonymousExample.java**
```java
package example05_418p;

public class AnonymousExample {

	public static void main(String[] args) {
		Anonymous a = new Anonymous();
		
		a.field.run();
		a.method1();
		a.method2(new Vehicle() {
			
			@Override
			public void run() {
				System.out.println("트럭이 달립니다.");
			}
		});
	}
}
```
   
 익명 객체 필드, 익명 객체 메소드, 익명 객체 매개 변수를 작성해보는 문제이다.

<br/><br/>
<hr/>
