# Interface 활용
Key Word : -able, 
<hr/>
   
### Starcraft의 건물(Building)으로 구조 익히기   
**만들고자 하는 원하는 스타크래프트 빌딩의 구조**   
   
![image](https://user-images.githubusercontent.com/84966961/121826382-5e3add00-ccf2-11eb-80ea-85108303e8f9.png)   
   
![image](https://user-images.githubusercontent.com/84966961/121832090-318fc100-cd04-11eb-845a-839af5d2f771.png)   

      
<br/><br/>
<hr/>
    
 1. 먼저 각 요소들의 클래스, 인터페이스 들을 만들어 준다.   
   
![image](https://user-images.githubusercontent.com/84966961/121826560-431c9d00-ccf3-11eb-84f5-a95208a40ff4.png)   
   
<br/><br/>
<hr/>
    
 2. Liftable interface에 각 건물의 기능에 대한 추상 메소드들을 정의해준다. (liftoff, move, stop, land)   
    
 ```java
 public interface Liftable {
	void liftoff();
	void move(int x,int y);
	void stop();
	void land();
}
```
   
 `Liftable Implements` 라는 배럭과 팩토리와의 포함 관계인 클래스를 만들어 줄 것이다. 왜 굳이 만들어서 사용하는 걸까?   

 => 만약 같은 기능을 가진 건물들(클래스)들이 무수히 많고 기능을 공통으로 한번에 정의해주는 객체가 없다면 일일히 수백개가 되는 클래스들을 다시 정의해주는 곤란한 상황이 생길 수 있다. 그런 상황을 방지하기 위해서 `Liftable Implements`를 만들어주고 한 곳에 기능을 모두 정의한 다음 기능이 필요한 건물들이 포함관계로 참조함으로써 구조의 단순화와 효율성을 높여 줄 수 있다.   
   
```java
public class LiftableImpl implements Liftable {
	@Override
	public void liftoff() {
		System.out.println("이륙한다.");
	}
	@Override
	public void move(int x, int y) {
		System.out.println("움직인다.");
	}
	@Override
	public void stop() {
		System.out.println("멈춘다.");
	}
	@Override
	public void land() {
		System.out.println("착륙한다.");
	}
}
```

<br/><br/>
<hr/>
    
 3. 이제 이 기능을 각각 Lift 기능을 넣어줄 건물들(배럭, 팩토리)에 포함시켜주면 될 것이다. 방법은 `LifatableImpl`의 인스턴스를 만들어서 호출해주면 될 것이다.   
   
```
public class Barrack extends Building implements Liftable{

	LiftableImpl lf = new LiftableImpl();
	
	@Override
	public void liftoff() {
		lf.liftoff();
	}

	@Override
	public void move(int x, int y) {
		lf.move(x, y);
	}

	@Override
	public void stop() {
		lf.stop();
	}

	@Override
	public void land() {
		lf.land();
	}
}
```
   
 이렇게 하면 배럭과 팩토리가 `LiftableImpl`과 포함 관계를 맺은 것이다. 이 상태에서 건물 부양의 기능을 바꾸고 싶다면 모든 건물의 메소드를 변경하는 것이 아니라 `LiftableImpl`만 수정해주면 될 것이다.
   
<br/><br/>
<hr/>
    
 4. 실행 클래스를 만들어서 테스트 해보자.   
   
```java
public class BuildTest {

	public static void main(String[] args) {

		Barrack ba = new Barrack();
		Factory ft = new Factory();
		
		ba.liftoff();
		ba.move(0, 0);
		ba.stop();
		ba.land();
		
		System.out.println("=================");
		
		ft.liftoff();
		ft.move(0, 0);
		ft.stop();
		ft.land();
	}
}
```
**실행 이미지**   
![image](https://user-images.githubusercontent.com/84966961/121827933-e7eda900-ccf8-11eb-80a2-ee2eb8da6e13.png)

 `LiftableImpl` 클래스 변경 후   
    
 ![image](https://user-images.githubusercontent.com/84966961/121828042-529ee480-ccf9-11eb-8b1e-75ab4c88f5f9.png)

   
<br/><br/>
<hr/>
    
 ### 교재 387p Example 4번      
**만들고자 하는 원하는 예제의 구조**   

 ![image](https://user-images.githubusercontent.com/84966961/121828851-ce9a2c00-ccfb-11eb-8d99-ff75f9e92a73.png)
   
 1. 예제 풀어보기.
```java
public class DaoExample {
	public static void dbWork(DataAccessObject dao) {
		dao.select();
		dao.insert();
		dao.update();
		dao.delete();
	}

	public static void main(String[] args) {
		dbWork(new OracleDao());
		dbWork(new MySqlDao());
	}
}
```
```java
public interface DataAccessObject {
	void select();
	void insert();
	void update();
	void delete();
}
```
```java
	@Override
	public void select() {
		System.out.println("Oracle DB에서 검색");
	}

	@Override
	public void insert() {
		System.out.println("Oracle DB에 삽입");
	}

	@Override
	public void update() {
		System.out.println("Oracle DB를 수정");
	}

	@Override
	public void delete() {
		System.out.println("Oracle DB에서 삭제");
	}
```

   
<br/><br/>
<hr/>
    
 2. OracleDao와 MySqlDao를 합쳐 어답터 구조로 만들어 보자.   
   
```java
public class OracleDao implements DataAccessObject {
	DaoImpl di = new DaoImpl();

	String DBname = "Oracle";
	
	@Override
	public void select() {
		System.out.print(DBname);
		di.select();
	}
	@Override
	public void insert() {
		System.out.print(DBname);
		di.insert();
	}
	@Override
	public void update() {
		System.out.print(DBname);
		di.update();
	}
	@Override
	public void delete() {
		System.out.print(DBname);
		di.delete();
	}
}
```
```java
public class DaoImpl implements DataAccessObject {
	
	@Override
	public void select() {
		System.out.println(" DB에서 검색");
	}

	@Override
	public void insert() {
		System.out.println(" DB에 삽입");
	}

	@Override
	public void update() {
		System.out.println(" DB를 수정");
	}

	@Override
	public void delete() {
		System.out.println(" DB에서 삭제");
	}

}
```
 위 코드 처럼 바꿔주면 어답터화 되는 것을 알 수 있다.
   
![image](https://user-images.githubusercontent.com/84966961/121832001-f097ac80-cd03-11eb-8893-919bcd010a54.png)   
   
<br/><br/><br/>
<hr/>

## 389p : 중첩 클래스와 중첩 인터페이스   
   
<hr/>
   
### 교재 390p ~ 395p 설명
   
```java
package nested.class1;

public class A {
	A() {
		System.out.println("A객체 생성");
	}
	
	class B{
		B() {
			System.out.println("B객체 생성");
		}
		
		int field;
		void method1() {
			
		}
	}
	static class C {
		C() {
			System.out.println("static한 C객체 생성");
		}
		
		int field;
		static int field2;
		void method1() {
			
		}
		static void method2() {
			
		}
		
	}
	
	void method() {
		class D {
			D() {
				System.out.println("void한 D객체 생성");
			}
			int field;
			void method() {
				
			}
		}
		D d = new D();
		d.field = 3;
		d.method();
	}
}
```
   
```java
package nested.class1;

public class InnerTest {

	public static void main(String[] args) {

		A a = new A();
		
		A.B b = a.new B();  //인스턴스한 내부클래스는 이렇게 생성 해야함.
		
		b.field = 3;
		b.method1();
		
		//------------------------------
		
		A.C c = new A.C();	// 정적 내부 클래스.
		c.field = 3;
		c.method1();
		A.C.field2 = 3 ; 	// 스태틱한 변수이기 때문에 인스턴스화 하지 않아도 호출 가능함.
		A.C.method2(); 		// 스태틱 메소드기 때문에 바로 호출 가능.

		//------------------------------
		
		a.method(); 		// 로컬 내부 클래스
		// 내부 클래스들을 실행 시키는 코드.
	}

}
```
   
 **a.method() 실행 결과**
   
![image](https://user-images.githubusercontent.com/84966961/121833197-ca274080-cd06-11eb-970d-4aea6d9ce0ab.png)
   
<br/><br/>   
<hr/>
   
### 교재 396p : 중첩 클래스의 접근 제한   
<br/> 
   
































