# Lecture15
Key Word : stop 플래그, interrupt(), interrupted(), isInterrupted(), 데몬 스레드(Daemon), 보조 스레드, isDaemon(), DiningTest(Cook, Table, Customer)

### 교재 613p 12.6.5 스레드의 안전한 종료(stop 플래그, interrupt())   
   
 스레드는 보통 run 메소드가 종료되면 종료되게 된다. 경우에 따라서는 중간에 스레드를 종료하고 싶은 경우가 생길텐데 Thread 는 스레드를 즉시 종료 시키기 위해서 `stop()` 메소드를 제공하고 있다. 현재는 `deprecated` 되었다. 그이유는 `stop()` 메소드로 스레드를 갑자기 종료하게 되면 스레드가 사용 중이던 자원들이 불안전한 상태로 남겨지기 때문이다. 여기서 자원 이란 파일, 네트워크 연결 등을 말한다.    


<br/>
<hr/>

**stop 플래그를 이용하는 방법**   
   
 스레드는 `run()` 메소드가 끝나면 자동적으로 종료되므로, `run()`메소드가 정상적으로 종료되도록 유도하는 것이 최선의 방법이다.   
   
 다음은 `whlie 문의 조건`을 이용해서 `run()`메소드를 정상적으로 유도하는 예제이다.

**1초 후 출력 스레드를 중지시킴**
 ```
public class StopFlagExample {
	public static void main(String[] args)  {
		PrintThread1 printThread = new PrintThread1();
		printThread.start();
		
		try {
			Thread.sleep(1000);						// 1초 대기
		} catch (InterruptedException e) {
		}
		
		printThread.setStop(true);					// 정지
	}
}
```
    
**whlie문에 들어있는 무한 반복해서 출력하는 스레드**
 ```
public class PrintThread1 extends Thread {
	private boolean stop;
	
	public void setStop(boolean stop) {
	  this.stop = stop;
	}
	
	public void run() {	
		while(!stop) {
			System.out.println("실행 중");
		}	
		System.out.println("자원 정리");
		System.out.println("실행 종료");
	}
}
```

**결과 화면**    
![image](https://user-images.githubusercontent.com/84966961/123184692-280a1400-d4cf-11eb-8eab-ae865487e81c.png)
    


<br/>
<hr/>

**interrupt() 메소드를 이용하는 방법**   
   
 `interrupt()` 메소드는 스레드가 일시 정지 상태에 있을 때 InterruptedException 예외를 발생시키는 역할을 한다. 이것을 이용하면 `run ()` 메소드를 정상 종료시킬 수 있다. 예를 들어 다음 그림과 같이 ThreadA가 Thread B를 생성해서 `start()` 메소드로 Thread B를 실행했다고 가정해보자.    
   
 위 예제를 수정하여 `interrupt()` 메소드로 종료시켜보자.   
   
**InterruptExample.java**
```java
public class InterruptExample {
	public static void main(String[] args)  {
		Thread thread = new PrintThread2();
		thread.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
		thread.interrupt();
	}
}

```
   
**PrintThread2.java**
```java
public class PrintThread2 extends Thread {
	public void run() {	
		try {
			while(true) {
				System.out.println("실행 중");
				Thread.sleep(1);
			}	
		} catch(InterruptedException e) {		
		}

		System.out.println("자원 정리");
		System.out.println("실행 종료");
	}
}
```

 간단하게 보면 상위 스레드에 interrupt 메소드를 걸어 놓은 상태로 하위 스레드에서 sleep이 발생하면 자동으로 예외처리에 의에 try에서 빠져나가는 것을 이용하여 해당 하위 스레드를 멈추는 것이다. (sleep은 InterruptException을 발생시키고 try catch로 예외 처리를 해주기 때문에)   
   
<br/><br/>
   
#### `interrup()`를 인식하는 `interrupted()`와 `isInterrupted()`
   
`interrup()`가 호출될 때 `interrupted()`와 `isInterrupted()`가 함께 호출된다. `interrupted()`와 `isInterrupted()`가 호출될 때 boolean 타입 `true`를 리턴한다.    
   
**PrintThread2.java와 interrupted()**
```java
public class PrintThread2 extends Thread {
	public void run() {			
 
		while(true) {
			System.out.println("실행 중");
			if(Thread.interrupted()) {			// interrupted()가 true를 호출하면서 break로 빠져나감.
				break;
			}
		}
		
		System.out.println("자원 정리");
		System.out.println("실행 종료");
	}
}
```


<br/><br/>
<hr/>
   
## 교재 618p 12.7 데몬 스레드(daemon)   
   
 데몬(daemon) 스레드는 주 스레드의 작업을 돕는 `보조적인 역할`을 수행하는 스레드이다. 주 스레드가 종료되면 데몬 스레드는 강제적으로 자동 종료되는데, 그 이유는 주 스레드의 보조 역할을 수행하므로 주 스레드가 종료되면 데몬 스레드의 존재 의미가 없어지기 때문이다.   
    
 **보조 스레드를 만드는 이유** : 주 스레드(데이터 처리)가 끝났음에도 다른 스레드(3분 마다 저장기능)가 끝나지 않아서 프로그램이 계속 실행되는 부작용이 생길 수 있으므로 주 스레드의 업무가 끝나면 자동적으로 강제 종료되는 보조 스레드를 만들어 사용한다.    
   
**AutoSaveThread.java**
```java
public class AutoSaveThread extends Thread {
	public void save() {
		System.out.println("작업 내용을 저장함.");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
			save();
		}
	}
}
```
   
**DaemonExample.java**
```java
public class DaemonExample {
	public static void main(String[] args) {
		AutoSaveThread autoSaveThread = new AutoSaveThread();
		autoSaveThread.setDaemon(true);				// 데몬 스레드 생성
		autoSaveThread.start();						//
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		
		System.out.println("메인 스레드 종료");
	}												// 이틈에도 데몬 스레드가 살아있고 실행될 수 있다.
}

```

#### 데몬인지 알고 싶을 땐 isDaemon()!   
   




#### Daemon이 죽는 시점   
   
 죽는 시점은 주스레드가 죽을 때까지 실행된다. 실례로 상위 코드의 주석처럼 `System.out.println("메인 스레드 종료");` 가 프린트되고 종료되는 짧은 사이에 다음 그림처럼 데몬 스레드가 실행될 수도 있다.   
   
**결과 화면**
![image](https://user-images.githubusercontent.com/84966961/123189076-1ed17500-d4d8-11eb-9790-1b4090b899c6.png)
   
#### 일반 스레드 2개 사용시   
   
```java
public class DaemonExample {
	public static void main(String[] args) {
		AutoSaveThread autoSaveThread = new AutoSaveThread();
//		autoSaveThread.setDaemon(true);				// 데몬 스레드 생성
		autoSaveThread.start();						//
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		
		System.out.println("메인 스레드 종료");
	}												// 이틈에도 데몬 스레드가 살아있고 실행될 수 있다.
}
```
   
 상위 코드 처럼 데몬을 없애면 메인이 끝나더라도 `AutoSaveThread` 스레드는 계속 돌아간다. 보조 스레드가 아니기 때문에 주 스레드가 사라져도 부 스레드는 남아있다.   
   
**결과 화면**    
![image](https://user-images.githubusercontent.com/84966961/123189306-8ab3dd80-d4d8-11eb-8876-284d6ad8384e.png)   
   

<br/><br/>
<hr/>
   

## 스레드 예제 
   
Cook, Customer, Table 클래스를 이용한 스레드 예제를 만들어보자. Cook은 음식을 만들어 Table에 채워 놓고, Customer는 Table의 음식을 받아 먹는다.   
   
**Customer.java**    
```java
public class Customer implements Runnable {

	private Table table;
	private String food;
	
	// 스레드 만들때 다음과 같은 형식으로 만들어야 함.
	public Customer(Table table, String food) {			// 매개변수를 넣게 하기 위한 생성자 생성.
		super();
		this.table = table;
		this.food = food;
	}


	@Override
	public void run() {

		while (true) {
			try {
				Thread.sleep(10);
				
			} catch (InterruptedException e) {
			}
			
			String name = Thread.currentThread().getName();
			
			if (eatFood()) {
				System.out.println(name+ " 음식을 잘 먹었다.");
			} else {
				System.out.println(name+ " 음식을 못 먹었다.");				
			}
		}
	}
	
	boolean eatFood() {
		
		return table.remove(food);						// food 값은 음식이고, remove의 결과가 boolean 이다.
	}
}

```

**Cook.java**    
```java
public class Cook implements Runnable {

	private Table table;
	
	// 스레드 만들때 다음과 같은 형식으로 만들어야 함.
	public Cook(Table table) {			// table로 초기화 하게끔 생성자 생성.
		super();
		this.table = table;
	}


	@Override
	public void run() {
		while (true) {
			int idx = (int)(Math.random()* table.dishNum());	// 0~2의 배열값이니까 +1안함.
			table.add(table.dishNames[idx]);					// 음식을 만듬.
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
			
		}
	}

}
```

**Table.java**    
```java
import java.util.ArrayList;

public class Table {
	String[] dishNames = {"donut", "donut", "burger"};		// 테이블에 올라갈 수 있는 음식을 배열로 정의함.
	
	final int MAX_FOOD = 6;									// 테이블에 최대로 올릴 수 있는 음식 갯수를 정함.

	private ArrayList<String> dishes = new ArrayList<>();	// 테이블에 저장할 수 있는 저장소를 배열리스트로 만듬.
	
	public void add(String dish) {							// 테이블에 음식을 저장하는 기능의 메소드
		if (dishes.size() >= MAX_FOOD) {					// 음식이 더 많으면 못만들도록
			return;
		}
		dishes.add(dish);									// 단순 배열이 아니라 객체 형태로 사용하기 때문에 
															// index 넘버로 구분하는 것이 아니라 add() 메소드를 이용하여 접시를 추가함.
		System.out.println("Dishes : "+ dishes.toString());
	}

	public boolean remove(String dishName) {				// 먹는다는 것은 배열에서 지운다는 것.
		
		for (int i = 0; i < dishes.size(); i++) {			// 테이블 위의 음식 갯수만큼 반복하여 확인함.
			if (dishName.equals(dishes.get(i))) {			// 매개 변수가 i번째 dishes 값이랑 같으면 
				dishes.remove(i);							// 삭제 : 소비자가 먹음.
				return true;
			}
		}
		
		return false;
	}
	
	public int dishNum() {									// 만들 수 있는 음식의 갯수.
		return dishNames.length;
	}
}
```

**DiningTest.java**    
```java
public class DiningTest {

	public static void main(String[] args) throws Exception {
		Table table = new Table();
		
		Cook cook = new Cook(table);
		Customer customer = new Customer(table, "donut");			// 도넛 먹는 사람.
		Customer customer2 = new Customer(table, "burger");			// 버거 먹는 사람.
		
		
		Thread cooker = new Thread(cook,"Cook");				
		Thread customer_donut = new Thread(customer,"Customer");
		Thread customer_burger = new Thread(customer2,"Customer2");

		cooker.start();
		customer_donut.start();
		customer_burger.start();
		
		Thread.sleep(100);											// main 스레드 o.1초 sleep
		System.exit(0);												// 강제로 프로그램 종료 하고 싶을 때 사용
		
	}

}
```









**두가지의 치명적 오류 발생**   
   
 스레드들간의 경쟁적으로 하다보면 요리사(Cook)와 고객(Customer)사이에서 예외가 발생하기도 하고, 고객(Customer1)과 고객(Customer2) 사이에서 문제가 발생하기도 한다.
   
1. java.util.ConcurrentModificationException : 
 Cook이 만드는 도중 Customer가 아직 완성되지 않은 정보를 삭제(remove())함.   
    
![image](https://user-images.githubusercontent.com/84966961/123196894-a96ca100-d4e5-11eb-961d-12d08d9e112e.png)    
   
 -> ArrayList에 접근하여 작성 중(add, remove)일 때 동기화(synchronized)를 시켜서 한 스레드만 접근 가능하도록 만들면 오류를 방지 시킬 수 있다.

2. IndexOutOfBoundsException : 
 Array는 중간 값이 사라지면 공백을 지우고 앞으로 달라붙는 성질이 있다. 이럴 때 Customer1이 먹고 공백을 당겨서 메꾸기 전에 Customer2가 그 다음 음식에 접근하게 되고 Customer1이 마무리해서 Array를 당기면 Customer2가 먹을 음식(데이터)가 사라져있어서 생기는 오류이다. ArrayList의 특성으로부터 발생되는 예외이다.   
   
![image](https://user-images.githubusercontent.com/84966961/123197636-c2298680-d4e6-11eb-8837-36c49ee7bf5c.png)   
   
 -> Remove에 동기화(synchronized)를 걸어서 Customer 들끼리 순서를 지켜 접근하도록 만든다.   
   

<br/><br/>

 이러한 문제점을 해결하기 위해서 wait와 동기화(synchronized)를 사용한다.   
   
 1. add와 remove에 동기화(synchronized)를 걸더라도 각 메소드를 사용하는 스레드가 다르기 때문에 의미가 없다. add는 Cook이 쓰고 Reomve는 Customer가 쓰기 때문에 오류 1번인 Cook과 Customer 사이의 문제는 해결하지 못한다.
 2. Cook과 Customer 사이의 문제를 해결하기 위해서 동기화 블록을 remove 내부에 걸어서 모든 동기화 대상에 대해서 그룹화시켜서 처리하도록 만든다. (동기화 블록을 사용하면 클래스 내부의 모든 동기화 메소드와 동기화 블록이 연동되어 잠금(lock)이 걸림.)    
   
**예외 해결을 위한 동기화 예제 코드**
```java
import java.util.ArrayList;

public class Table {
	String[] dishNames = { "donut", "donut", "burger" }; 				// 테이블에 올라갈 수 있는 음식을 배열로 정의함.

	final int MAX_FOOD = 6; 											// 테이블에 최대로 올릴 수 있는 음식 갯수를 정함.
			
	private ArrayList<String> dishes = new ArrayList<>(); 				// 테이블에 저장할 수 있는 저장소를 배열리스트로 만듬.

	public synchronized void add(String dish) { 						// 테이블에 음식을 저장하는 기능의 메소드
		if (dishes.size() >= MAX_FOOD) { 								// 음식이 더 많으면 못만들도록
			return;
		}
		dishes.add(dish); 												// 단순 배열이 아니라 객체 형태로 사용하기 때문에
																		// index 넘버로 구분하는 것이 아니라 add() 메소드를 이용하여 접시를 추가함.
		System.out.println("Dishes : " + dishes.toString());
	}

	public boolean remove(String dishName) { 							// 먹는다는 것은 배열에서 지운다는 것.
		synchronized (this) {											// 동기화 블록을 시키면 클래스 내부의 모두가 동기화 연동됨.
			for (int i = 0; i < dishes.size(); i++) { 					// 테이블 위의 음식 갯수만큼 반복하여 확인함.
				if (dishName.equals(dishes.get(i))) { 					// 매개 변수가 i번째 dishes 값이랑 같으면
					dishes.remove(i); 									// 삭제 : 소비자가 먹음.
					return true;
				}
			}

			return false;
		}
	}

	public int dishNum() { 												// 만들 수 있는 음식의 갯수.
		return dishNames.length;
	}
}
```
   
예외 처리 완료.

### 퍼포먼스를 올리기 위한 예제 수정.
    

<br/><br/>
<hr/>

