# Interface 복습
Key Word : 인터페이스(Interface), 클래스(class), 캐스팅(Casting), toString, 콜백(call back)
<hr/>
   
 ### 344p ~ 375p : 인터페이스와 객체 구조 복습
   
   다음과 같은 구조로 0609 Tire 예제를 변형하여 Casting의 필요성과 다중 상속, 참조에 대해 정리해보았다.
![image](https://user-images.githubusercontent.com/84966961/121624641-d617c600-caac-11eb-8f14-a091fca2c57d.png)






캐스팅을 해주는 이유.

TireInfo의 로케이션을 가져오기 때문.

한국타이어로 캐스팅을 해줘야하는데 왜 타이어인포로 하냐면 부모인 타이어인포로해도 어떤 타입을하더라도 똑같은 로케이션을 가지고있기 때문에 금호와 한국을 모두 쓸수있는 타이어인포가 좋다.



<hr/>

 ### 스타크래프트 유닛을 이용한 예제
 
 **예제 계층 구조**
 
 ![image](https://user-images.githubusercontent.com/84966961/121621948-0e68d580-caa8-11eb-8cb7-d58a461fcfa3.png)
   
 구조에 따라서 Starcraft 패키지 내부에 각각의 클래스들을 상속 구조를 이용해 만들어 보자.   
    
 다음과 같은 유닛에 필요한 속성들을 상속을 통해 정의할 수 있도록 구성해보자.   
    
 모든 Unit의 공통 속성 
   1. 체력(hitPoint) : 실제 변화되는 체력값임.   
   2. 체력을 회복(heal)시켜주는 유닛도 있기 때문에 체력의 최댓값을 정해주고 이는 정해진 상수이다.
     또한, 상수는 초기값을 정해주어야 한다. 하지만 각 유닛마다 다르게 해주기 위하여 생성자를 만들 때 초기화 할 수 있는 상수의 특성을 이용하기 위해 생성자에 넣어준다.   
   3. 부모에게 생성자를 정해주면 모든 자식들에게도 생성자를 만들어줘야 한다.   
```java
public class Unit {
	int hitPoint;
	final int MAX_HP; // 유닛마다 최대체력이 존재한다.
	
	public Unit(int mAx_HP) {  // 부모 생성자를 만들어 주었으면 자식에서 생성자를 호출해주어야만 한다.
		super(); // 모든 최상위 생성자는 object이라는 생성자를 상속받는다.
		MAX_HP = mAx_HP; // 상수는 최초 혹은 생성자 생성시에만 초기화가 가능하다.
	}	}
```
   4. 최하위 유닛들의 최대 체력치는 정해져 있으므로 Marine(40), SCV(60), Tank(150), Dropship(125) 이렇게 고정값으로 각각 정해준다.   
   5. 유닛 초기 생성시 체력(hitPoint)은 최대 체력치(MAX_HP)와 같으므로 유닛마다 코드 작성을 해준다.   
```java
public class Marine extends GrountUnit {

	public Marine() {
		super(40);
		hitPoint = MAX_HP;	}}
```
   6. SCV는 기계 유닛(Tank, Dropship)을 치료, 수리할 수 있다. 이는 기능이므로 메소드(repair)로 구현해준다.   
   7. repair라는 기능은 기계 유닛을 구분할 수 있어야 한다. 치료할 유닛을 매개 변수로 받아 구분해준다. 오버로딩 기능   
```java
	void repair(Tank T) { }
	void repair(Dropship D) { } // 메소드 오버로딩
```
   
 하지만 유닛이 종류가 많아 질수록 메소드가 늘어남으로 매개 변수 다형성의 특징을 이용해 한 타입으로 합쳐준다.
```java
	void repair(Unit u) { }
```
   
 그러나 이런 경우에는 마린같은 생체 유닛도 치료가 될 수 있으므로 if문을 일일히 다 넣어줘야 할 것이다. 하지만 무수히 많은 유닛을 일일히 if문으로 작성해줄 수 없으므로 탱크와 드롭쉽을 기계라는 카테고리로 묶어주는 방법을 이용해보자. 이 경우 클래스로도 가능하지만 클래스는 객체를 생성하기 위한 용도이며, 클래스는 단일 상속만 가능하다. 그렇기 때문에 이런 경우 사용할 수 있는 것이 Interface 생성을 통한 참조이다. Repairable이라는 Interface를 기계 유닛에 참조시켜 각 유닛들을 그룹화(?)해준다.
```java
   public class SCV extends GrountUnit implements Repairable { }
   public class Tank extends GrountUnit implements Repairable { }
   public class Dropship extends AirUnit implements Repairable { }
```
   8. 그 다음 Repair의 기능을 구현해준다.   
```java
    void repair(Repairable r) { // 치료가 필요한 것들.
	if(r instanceof Tank) {} 
	else if (r instanceof Dropship) {}
      ...
	}
```
 종류가 많아질수록 if문이 무한히 늘어날 것이므로 if문의 조건 또한 다형성에 기반하여 묶어준다.   
```java
	void repair(Repairable r) { // 치료가 필요한 것들.
		if(r instanceof Unit) {
			((Unit) r).hitPoint++;
		}
	}
```
  Repairable r에는 체력이 없으므로 유닛으로 캐스팅하여 기능을 구성해준다. 이 후 체력이 무한히 증가할 수는 없으므로 MAX_HP값에 도달하면 체력 회복이 완료되었다는 기능과 함께 메세지를 출력하도록 구현해준다.   
```java
	void repair(Repairable r) { // 치료가 필요한 것들.
		if (r instanceof Unit) {
			Unit u = (Unit)r;
         
			while(u.hitPoint != u.MAX_HP) {
				((Unit) r).hitPoint++;
			}
			System.out.println("수리 완료");
		}	}
```
   9. StarcraftTest 클래스를 만들어 지금까지의 결과를 실행해보자.   
```java
public class StarcraftTest {
	public static void main(String[] args) {
		SCV s = new SCV();
		Tank t = new Tank();
		Dropship d = new Dropship();
		Marine m = new Marine();

		s.repair(t);
		s.repair(d);
	}}
```
   
  **실행화면**    
     
  ![image](https://user-images.githubusercontent.com/84966961/121627500-6ad0f280-cab2-11eb-97f7-a9ed1160bb60.png)   
     
   10. 하지만, 무엇이 수리가 완료되었는지 궁금하므로 toString의 override를 이용하게 되면 단순히 u를 출력하더라도 주소값이 아닌 toString을 호출하므로 따로 이름을 불러오는 함수를 추가하는 것이 아니라 toString을 사용한다.(CallBack 함수)   
   
% toString에 대한 내용은 맨 밑 참조에 넣어 놓음.   
   
![image](https://user-images.githubusercontent.com/84966961/121627645-b6839c00-cab2-11eb-940e-673b330cd4ea.png)
   ```java
	public void repair(Repairable r) { // 치료가 필요한 것들.
		if (r instanceof Unit) {
			Unit u = (Unit)r;
			
			while(u.hitPoint != u.MAX_HP) {
				((Unit) r).hitPoint++;
			}
			System.out.println(u +" 수리 완료");  }}
```
   
  위의 설명처럼 단순히 u를 불러도 이름값이 나오는 것을 알 수 있음.   
     
**실행화면**   
   
  ![image](https://user-images.githubusercontent.com/84966961/121628135-a0c2a680-cab3-11eb-90d0-7c81be50f345.png)   
   
11. UML 다이어그램을 그릴 수 있도록 이클립스 추가 플러그인을 설치후 ucls 파일을 생성함.   
   
**Starcraft 패키지의 UML Diagram**   
   
![image](https://user-images.githubusercontent.com/84966961/121635187-c609e180-cac0-11eb-951c-ab0c7d4bd51c.png)
   
   
   12. 다음과 같은 구조로 Medic과 인터페이스 추가해보자.   
   
![image](https://user-images.githubusercontent.com/84966961/121635451-387ac180-cac1-11eb-8e12-852481295ab9.png)   
   
 **작성 후**   
      
![image](https://user-images.githubusercontent.com/84966961/121636382-cacf9500-cac2-11eb-8723-95b020f96464.png)    
   

   

<hr/>

 ## 객체 값 출력_toString 메소드 오버라이딩

[ toString 메소드 ]

기본 상속된 Object 클래스의 메소드   
객체가 가진 정보를 문자열로 만들어 출력   
인스턴스 출력 시 Object.hashCode()로 생성된 해시값을 문자열로 반환시켜줌   
인스턴스의 값을 출력할 때 그냥 인스턴스를 넣거나 기본 toString()메소드를 사용하면 해시값을 포함한 이상한 값이 출력됩니다. 만들어 둔 인스턴스의 정보를 편하게 출력하기 위해 toString()메소드를 오버라이딩 해두면 편리합니다.

<hr/>   
   
**콜백(callback)**

 1. 피호출자(Callee)가 호출자(Caller)를 다시 호출하는 것   
 2. 비동기적 처리를 하기 위한 디자인 패턴의 종류   
   
간단하게는 위와 같은 개념을 가지고 있지만, 저는 이렇게 이해하는 것이 더 명확하게 느껴졌습니다.
   
콜백함수를 등록하면, 특정 이벤트가 발생되었을 때 콜백함수를 호출하여 실행한다. 즉, 콜백함수는 바로 사용할 수도 있으며, 추후에 사용할 수도 있다. 이때 콜백함수가 등록되는 곳과 이벤트가 발생할 때 호출하는 것은 서버이다.
   
이때, 특정 이벤트라는 것은 어떠한 조건을 만족시키는 것을 의미합니다.
   
   
   
<hr/> 

 ## UML Diagram 생성기 설치 방법.   
    
 1. Help > Install New Software... 클릭.
   
![image](https://user-images.githubusercontent.com/84966961/121830791-d4ded700-cd00-11eb-91e3-3285f5d47450.png)   
   
   
 <br/><br/><br/>
 2. add 버튼 클릭.
   
![image](https://user-images.githubusercontent.com/84966961/121830907-20918080-cd01-11eb-9335-1cf830bdc517.png)   
   
   
 <br/><br/><br/>
 3. https://www.objectaid.com/download 홈페이지의 값을 이클립스 창에 기입.    
   
![image](https://user-images.githubusercontent.com/84966961/121830980-5171b580-cd01-11eb-9921-dd10d9310207.png)   
   
![image](https://user-images.githubusercontent.com/84966961/121831019-677f7600-cd01-11eb-9b40-1b8afa050e18.png)   
   
   
 <br/><br/><br/>
 4. add 버튼 클릭 후 다음 그림처럼 체크박스 클릭.   
   
![image](https://user-images.githubusercontent.com/84966961/121831061-82ea8100-cd01-11eb-9821-114908bc2a63.png)   
   
 <br/><br/><br/>
 5. Next를 눌러 진행하다 term 동의 버튼 클릭 후 설치 진행.   
   
![image](https://user-images.githubusercontent.com/84966961/121831121-aa414e00-cd01-11eb-9dc5-c968843778cc.png)   
   
 설치를 진행하면 다음과 같이 이클립스 하단 부분에 초록색 창으로 설치 진행중인 것이 보인다.
   
 ![image](https://user-images.githubusercontent.com/84966961/121831229-ee345300-cd01-11eb-8cb4-79ee130beffc.png)   
   
 설치 후 재실행.
    
  <br/><br/><br/>
 6. 생성을 원하는 패키지에 오른쪽 버튼 클릭을 하여 New > Other... 클릭.   
   
![image](https://user-images.githubusercontent.com/84966961/121831385-49664580-cd02-11eb-9e3f-8d4bc1b3d9d6.png)   
   
 <br/><br/><br/>
 7. UML을 검색하여 UML ObjectAid class Diagram 클릭 후 다음 페이지로.   
   
 ![image](https://user-images.githubusercontent.com/84966961/121831475-816d8880-cd02-11eb-91da-8f3d186d4daa.png)   
   
 <br/><br/><br/>
 8. 원하는 이름 입력 후 생성.   
   
![image](https://user-images.githubusercontent.com/84966961/121831532-a7932880-cd02-11eb-8ee0-83abb1fe49fd.png)   
   
 <br/><br/><br/>
 9. 구조를 보고 싶은 파일들을 그래그 앤 드롭(drag n drop).   
    
 ![image](https://user-images.githubusercontent.com/84966961/121831644-e7f2a680-cd02-11eb-91c0-fa024ed145c1.png)   
    
    
 <br/><br/><br/>
 10. UML Diagram 생성!   
   
![image](https://user-images.githubusercontent.com/84966961/121831683-05c00b80-cd03-11eb-9184-175034367334.png)   

