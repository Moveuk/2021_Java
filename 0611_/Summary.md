#
Key Word : 
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
  ![image](https://user-images.githubusercontent.com/84966961/121628135-a0c2a680-cab3-11eb-90d0-7c81be50f345.png)

   11. a
   12. a
   13. a
   14. a
   15. 






















