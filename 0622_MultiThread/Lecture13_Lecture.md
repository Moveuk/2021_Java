# 멀티 스레드 (MultiThread)
Key Word : 멀티 스레드, 스레드 생성 방법 두 가지, 스레드의 이름(getName(), setName("스레드 이름")), 스레드 우선순위(Priority), 스레드 동기화 메소드와 동기화 블록

<hr/>
   
## 교재 576p : 12.1 멀티 스레드 개념

### 교재 576p : 12.1.1 프로세스와 스레드

 프로세스 : 운영체제에서 실행 중인 하나의 애플리케이션.
 멀티 태스킹(multi tasking) : 두 가지 이상의 작업을 동시에 처리하는 것.
 스레드(thread) : 사전적 의미로 `한 가닥의 실` 이라는 뜻인데, 한 가지 작업을 실행하기 위해 순차적으로 실행할 코드를 `실`처럼 이어 놓았다고 해서 유래된 이름임.   
   
 멀티 프로세스가 애플리케이션 단위의 멀티 태스킹이라면 멀티 스레드는 애플리케이션 내부에서의 멀티 태스킹이라고 볼 수 있다.   
   
![image](https://user-images.githubusercontent.com/84966961/122843654-e7799180-d33a-11eb-9a19-0a8ea4c1dcb3.png)   
   
 위 그림을 보면 스레드끼리는 스택만을 비공유하고 데이터 영역, 힙 영역은 공유 중인 것을 알 수 있다. `멀티 프로세스` 기반으로 프로그래밍할 때는 프로세스 간 공유하는 자원이 없기 때문에 동일한 자원에 동시에 접근하는 일이 없었지만 `멀티 쓰레딩`을 기반으로 프로그래밍할 때는 이 부분을 신경써줘야 한다.   
   
 서로 다른 쓰레드가 `데이터와 힙 영역을 공유`하기 때문에 어떤 쓰레드가 다른 쓰레드에서 사용중인 변수나 자료 구조에 접근하여 엉뚱한 값을 읽어오거나 수정할 수 있기 때문에 주의해야 한다. 그렇기 때문에 멀티 쓰레딩 환경에서는 `동기화 작업`이 필요하다. 동기화를 통해 `작업 처리 순서`를 컨트롤하고 `공유 자원`에 대한 접근을 컨트롤하는 것이다. 하지만 이로 인해 `병목 현상`이 발생하여 성능이 저하될 가능성이 높다. 그러므로 과도한 `락(lock)`으로 인한 병목 현상을 줄여야 한다.    
    
 공유 자원이 아닌 부분은 동기화 처리 할 필요가 없다. 즉, 동기화 처리가 필요한 부분에만 `synchronized` 키워드를 통해 동기화하는 것이다. 불필요한 부분까지 동기화를 할 경우, 현재 쓰레드는 `락(lock)을 획득한 쓰레드가 종료하기 전까지 대기`해야한다. 그렇게 되면 전체 성능에 영향을 미치게 된다. 즉, 동기화를 하고자 할 때는 `메소드 전체를 동기화 할 것인가 아니면 특정 부분만을 동기화할 것인지 고민`해야 한다.

<br/><br/>
<hr/>

## 교재 579p : 12.2 작업 스레드 생성과 실행   
   
![image](https://user-images.githubusercontent.com/84966961/122844352-7509b100-d33c-11eb-8fc1-98ea24f631d3.png)
   
 스레드를 생성 하는 방법은 `java.lang.Thread 클래스를 직접 객체화(Runnable 상속)`해서 생성해도 되지만, `Thread를 상속`해서 하위  클래스를 만들어 생성할 수도 있다.

<br/><br/>
<hr/>

### 교재 579p : 12.2.1 작업 스레드 생성과 실행   
    
 Runnable을 구현한 클래스를 Thread 객체로 생성해주어야 실제 스레드가 생성된다.

```java
Runnable task = new Task();

Thread thread = new Thread(task); // 구현 클래스를 매개 변수로 넣어 스레드를 구체화함.
```
   
 혹은 코드를 좀 더 절약하기 위해 Thread 생성자를 호출할 때 Runnable 익명 객체를 매개값으로 사용할 수 있다. 오히려 이 방법이 많이 사용된다.   
   
```java
Thread thread = new Thread( new Runnable() { 		// 익명 구현 객체.
	public void run() {
		// 스레드가 실행할 코드;
	}
} );
```
 함수적 인터페이스가 아닌 람다식을 매개값으로 사용할 수도 있다. 자바 8부터 지원되는 이 방법이 가장 간단한 방법이다.   
   
```java
Thread thread = new Thread( () -> { 		// 익명 구현 객체.
		// 스레드가 실행할 코드;
	}
} );
```
   

 작업 스레드 만들고 나서 즉시 실행되는 것이 아니라 `start()` 메소드를 다음과 같이 호출해야만 비로소 실행된다.   
   
```java
	thread.start();
```
   
 `start()` 메소드가 호출되면, 작업 스레드는 매개값으로 받은 `unnable의 run() 메소드`를 실행하면서 자신의 작읍을 처리한다.   
   
 스택 구조에 의해서 run() 메소드를 실행하면 기존의 스택에 쌓아가면서 실행을 하게 된다. 그렇기 때문에 run()메소드를 실행하는 것이 아니라 start() 메소드를 실행하여 새로운 스택을 쌓아 실행되도록 하는 것이다.   
   
![image](https://user-images.githubusercontent.com/84966961/122845191-15140a00-d33e-11eb-9d3a-4ba330acbeeb.png)   
   
 이렇게 사용하게 되면 main()과 run()이 두 스택을 통해 공존하는 상태가 된다. run()이 다 실행되고 나면 사라지고 main()으로 돌아가게 된다. thread가 thread를 만들면 자식부터 실행되고 자식 스레드부터 연쇄적으로 종료되게 된다.   
   
![image](https://user-images.githubusercontent.com/84966961/122845440-c024c380-d33e-11eb-8a0a-20b3a550c697.png)   
   
 이런 방식으로 작동하게 만들기 위해서 단순히 run()을 실행시키는 것이 아닌 스레드의 start() 메소드를 사용하는 것이다.   
   
<br/>
<hr/>
   
**비프와 프린트 동시에 출력하기**    
   
**BeepTask.java : 비프 소리 기능**   
```java
import java.awt.Toolkit;

public class BeepTask implements Runnable {
	public void run() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		for (int i = 0; i < 5; i++) {
			toolkit.beep();
			try {
				Thread.sleep(500);
			} catch (Exception e) {	}
		}
	}
}
```
**BeepPrintExample.java**   
```java
public class BeepPrintExample {
	public static void main(String[] args) {
		Runnable beepTask = new BeepTask();
		Thread thread = new Thread(beepTask);
		thread.start();
		
		for (int i = 0; i < 5; i++) {
			System.out.println("띵");
			try { Thread.sleep(500); }
			catch (Exception e) {}
			}
	}

}
```
   
 이 두 클래스를 나누지 말고, 익명 객체 혹은 람다식으로 합쳐 표현할 수 있다.   
   
   
**익명 객체와 Runnable을 이용한 스레드**
```java
public class BeepPrintExample_Runnable {

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				for (int i = 0; i < 5; i++) {
					toolkit.beep();
					try {
						Thread.sleep(500);
					} catch (Exception e) {
					}
				}
			}
		});

		thread.start();

		for (int i = 0; i < 5; i++) {
			System.out.println("띵");
			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
		}
	}
}
```
   
**람다식을 이용한 스레드**
```java
public class BeepPrintExample_lambda {

	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			for (int i = 0; i < 5; i++) {
				toolkit.beep();
				try {Thread.sleep(500);} 
				catch (Exception e) {}
			}
		});
		thread.start();

		for (int i = 0; i < 5; i++) {
			System.out.println("띵");
			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
		}
	}
}
```
   
<br/><br/>
<hr/>

### 교재 583p : 12.2.2 Thread 하위 클래스로부터 생성   
       
 작업 스레드가 실행할 작업을 Runnable로 만들지 않고, Thread의 하위 클래스로 작업 스레드를 정의하면서 작업 내용을 포함시킬 수도 있다. 다음은 작업 스레드 클래스를 정의하는 방법인데, Thread 클래스를 상속한 후 run 메소드를 재정의(overriding)해서 스레드가 실행할 코드를 작성하면 된다. 작업 스레드 클래스로부터 작업 스레드 객체를 생성하는 방법은 일반적인 객체를 생성하는 방법과 동일하다.   
   
```java
public class WorkerThread extends Thread {
	@override
	public void run() {
		//스레드가 실행할 코드;
	}
}
Thread thread = new WorkerThread();
```
   
 코드를 좀 더 절약 하기 위해 다음과 같이 익명 객체로 작업 스레드 객체를 생성할 수도 있다.   
    
```java
Thread thread - new Thread() {			// 익명 자식 객체
	public void run() {
		//스레드가 실행할 코드;
	}
};
```
   
 이렇게 생성된 작업 스레드 객체에서 `start()` 메소드를 호출하면 작업 스레드는 자신의 `run()` 메소드를 실행하게 된다.   
   
 기존 예제를 이용하여 Thread 클래스를 상속받아 처리하는 코드를 구성해보자.   
   
**BeepThread.java**
```java
package thread;

import java.awt.Toolkit;

public class BeepThread extends Thread {
	public void run() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		for (int i = 0; i < 5; i++) {
			toolkit.beep();
			try {
				Thread.sleep(500);
			} catch (Exception e) {	}
		}
	}
}
```

**BeepPrintExample_Thread.java**
```java
package thread;
// Thread 상속 클래스를 이용한 thread 실행.
public class BeepPrintExample_Thread {
	public static void main(String[] args) {
		Thread thread = new BeepThread();
		thread.start();
		
		for(int i=0; i<5; i++) {
			System.out.println("띵");
			try { Thread.sleep(500); } catch(Exception e) {}
		}
	}
}
```

위 코드를 다른 방식으로 작성할 수도 있다.

```java
package thread;

import java.awt.Toolkit;

// Thread 상속 클래스를 이용한 thread 실행의 대체.
public class BeepPrintExample_Thread_Alter {
	public static void main(String[] args) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				for (int i = 0; i < 5; i++) {
					toolkit.beep();
					try {
						Thread.sleep(500);
					} catch (Exception e) {	}
				}
			}
		};
		thread.start();
		
		for(int i=0; i<5; i++) {
			System.out.println("띵");
			try { Thread.sleep(500); } catch(Exception e) {}
		}
	}
}
```

   

   
<br/><br/>
<hr/>

### 교재 586p : 12.2.3 스레드의 이름   
   
 스레드는 자신의 이름을 가지고 있다. 스레드의 이름은 큰 역할을 하는 것은 아니지만, 디버깅할 때 어떤 스레드가 어떤 작업을 하는지 조사할 목적으로 가끔 사용한다. 메인 스레드의 니름은 `main`이라는 이름을 가지고 우리가 직접 생성한 스레드는 자동적으로 `Thread-n`이라는 이름으로 설정된다. `Tread-n` 대신 다른 이름으로 설정하고 싶다면 Thread 클래스의 `setName()` 메소드로 변경하면 된다.   
   
```java
	thread.setName("스레드 이름");
```
 반대로 스레드 이름을 알고 싶은 경우에는 `getName()` 메소드를 호출하면 된다.   
   
```java
	thread.getName();
```
   
 `setName()`과 `getName()` 메소드는 Thread의 인스턴스 메소드이므로 스레드 객체의 참조가 필요하다. 만약 스레드 객체의 참조를 가지고 있지 않다면 Thread의 정적 메소드인 `currentThread()`로 코드를 진행하는 현재 스레드의 참조를 얻을 수 있다.   
   
```java
	Thread thread = Thread.currentThread();
```
   
 `스레드는 언제나 순서대로 실행되지 않는다.`   
    
 다음 ㅇ제는 메인 스레드의 참조를 얻어 스레드 이름을 콘솔에 출력하고, 새로 생성한 스레드의 이름을 `setName()` 메소드로 설정한 후, `getName()` 메소드로 읽어오도록 했다.   
 
**ThreadNameExample.java**
```java
public class ThreadNameExample {
	public static void main(String[] args) {
		Thread mainThread = Thread.currentThread();
		System.out.println("프로그램 시작 스레드 이름: " + mainThread.getName());
		
		ThreadA threadA = new ThreadA();		
		ThreadB threadB = new ThreadB();
		
		System.out.println("작업 스레드 이름: " + threadA.getName());
		threadA.start();
		System.out.println("작업 스레드 이름: " + threadB.getName());
		threadB.start();
	}
}
```
  
**ThreadA.java**
```java
public class ThreadA extends Thread {	
	public ThreadA() {
		setName("ThreadA");
	}
	
	public void run() {		
		for(int i=0; i<2; i++) {		
			System.out.println(getName() + "가 출력한 내용");
		}
	}
}
```
  
**ThreadB.java**
```java
public class ThreadB extends Thread {	
	public void run() {		
		for(int i=0; i<2; i++) {		
			System.out.println(getName() + "가 출력한 내용");
		}
	}
}
```
 
 위의 Example 코드를 돌리다보면 스레드 A와 B가 병렬적으로 경쟁하면서 돌다가 다음과 같이 순서가 꼬이는 경우를 보게 된다. 이는 스레드의 `동시성`과 `병렬성`을 보여주는 사례이다.   
   
**스레드가 병렬적으로 진행되는 화면**
 ![image](https://user-images.githubusercontent.com/84966961/122852236-40e9bc80-d34b-11eb-89b9-f80588627f27.png)

 
   
<br/><br/>
<hr/>

## 교재 588p : 12.3 스레드 우선순위   
   
 동시성(Concurrency) 또는 병렬성(Parallelism)   
    
![image](https://user-images.githubusercontent.com/84966961/122850682-a4261f80-d348-11eb-8808-9e0fe8414c09.png) 

 싱글 코어 CPU의 경우에 멀티 스레드 작업을 병렬적으로 실행하는 것처럼 보이지만 빠른 속도로 처리하여 동시에 처리하는 것처럼 보인다. 하지만, 사실은 번갈아가며 실행하는 동시성 작업이다. 스레드의 개수가 코어 수보다 많을 경우, 스레드를 어떤 순서에 의해 동시성으로 실행할 것인가를 결정해야 하는데, 이것을 스레드 스케줄링이라고 한다. 스레드 스케줄링에 의해 스레드들은 아주 짧은 시간에 번갈아 가면서 그들의 run() 메소드드들을 조금씩 실행한다.   

   
![image](https://user-images.githubusercontent.com/84966961/122850688-a7b9a680-d348-11eb-94f3-bc860494c18c.png)
   
 자바의 스레드 스케줄링은 우선순위(Priority) 방식과 순환 할당(Round-Robin) 방식을 사용한다. 우선순위 방식은 우선순위가 높은 스레드가 실행 상태를 더 많이 가지도록 스케줄링하는 것을 말한다. 순환 할당 방식은 시간 할당량(Time Slice)을 정해서 하나의 스레드를 정해진 시간만큼 실행하고 다시 다른 스레드를 실행하는 방식을 말한다. 스레드 우선순위 방식은 스레드 객체에 우선순위 번호를 부여할 수 있기 때문에 개발자가 코드로 제어할 수 있다. 하지만 순환 할당 방식은 자바 가상 기계에 의해서 정해지기 때문에 코드로 제어할 수 없다.   
 
 우선순위 방식에서 우선순위는 1에서부터 10까지 부여되는데, 1이 가장 우선순위가 낮고, `10이 가장 높다.` 우선순위를 부여하지 않으면 모든 스레드들은 기본적으로 5의 우선순위를 할당받는다. 만약 우선순위를 변경하고 싶다면 Thread 클래스가 제공하는 `setPriority()` 메소드를 이용하면 된다.   
   
```java
	thread.setPriority(우선순위);
```
   
 **스레드의 우선순위 상수값**   

```java
	thread.setPriority(Thread.MAX_PRIORITY);	// 10
	thread.setPriority(Thread.NORM_PRIORITY);	//  5
	thread.setPriority(Thread.MIN_PRIORITY);	//  1	
```
   
 코어 수에 따라 스레드의 우선순의가 적용되는지 결정된다. 예를 들어 쿼드 코어는 코어가 4개이므로 우선순위의 영향을 받으려면 사용하는 스레드의 수가 5개 이상이 되어야 우선순위에 따라 처리 속도가 달라지게 된다.   
   
 다음은 우선순위 차이에 따른 처리 속도 차이를 보기 위한 예제이다.   
   
**CalcThread.java**
```java
public class CalcThread extends Thread {
	public CalcThread(String name) {
		setName(name);
	}
	
	public void run() {
		for(int i=0; i<2000000000; i++) {
		}
		System.out.println(getName());
	}
}
```

**PriorityExample.java**
```java
public class PriorityExample {
	public static void main(String[] args) {
		for(int i=1; i<=10; i++) {
			Thread thread = new CalcThread("thread" + i);
			if(i != 10) {
				thread.setPriority(Thread.MIN_PRIORITY);
			} else {
				thread.setPriority(Thread.MAX_PRIORITY);
			}
			thread.start();
		}
	}
}
```

**결과 화면**
![image](https://user-images.githubusercontent.com/84966961/122854644-fcf8b680-d34e-11eb-9e4a-45a1321e7439.png)
   
스레드 10번이 마지막에 생성되어 실행되었음에도 가장 먼저 끝나는 것을 확인할 수 있다. 이것이 스레드 우선순위에 따른 차이이다.   
   
 학원 컴퓨터의 CPU 사양이 4코어 8스레드이기 때문에 10개의 스레드를 돌릴 경우 우선순위가 영향을 미치는 것을 알 수 있다.
   
![image](https://user-images.githubusercontent.com/84966961/122854832-477a3300-d34f-11eb-81d0-32875abe5cd0.png)
   
<br/><br/>
<hr/>

## 교재 591p : 12.4 동기화 메소드와 동기화 블록   
   
### 교재 591p : 12.4.1 공유 객체를 사용할 때의 주의할 점   
   
 싱글 스레드 프로그램에서는 한 개의 스레드가 객체를 독차지할 수 있지만 다중 스레드 작업에서는 스레드들이 객체를 공유해야만 한다. 이 경우, 스레드 A를 사용하던 객체가 스레드 B에 의해 상태가 변경될 수 있기 때문에 스레드 A가 의도했던 것과는 다른 결과를 산출할 수도 있다.
   
![image](https://user-images.githubusercontent.com/84966961/122855118-b48dc880-d34f-11eb-80d7-572ff8435347.png)   
 
 User1 스레드가 Calculaor 객체의 memory 필드에 100을 먼저 저장하고 2초간 일시 정지 상태가 된다. 그 동안에 User2 스레드가 memory 필드값을 50으로 변경한다. 2초가 지나 User1스레드가 다시 실행 상태가 되어 memory 필드의 값을 출력하면 User2가 저장한 50이 나온다.   

 이 말은 스레드끼리 경쟁적으로 작업을 처리하다가 공유 메모리를 사용함으로써 서로 변질시킬 수 있다는 말이다.

**MainThreadExample.java**
```java
package unsynchronized;

public class MainThreadExample {
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		
		User1 user1 = new User1();			// User1 스레드 생성
		user1.setCalculator(calculator);	// 공유 객체 생성
		user1.start();						// User1 스레드 시작

		User2 user2 = new User2();			// User2 스레드 생성
		user2.setCalculator(calculator);	// 공유 객체 생성
		user2.start();						// User2 스레드 시작
	}
}
```

**Calculator.java**
```java
public class Calculator {
	private int memory;

	public int getMemory() {
		return memory;
	}

	public void setMemory(int memory) {			// 계산기 메모리에 값을 저장하는 메소드
		this.memory = memory;					// 매개값을 memory 필드에 저장
		try {									// 스레드를 2초간 일시 정지시킴
			Thread.sleep(2000);
		} catch(InterruptedException e) {}	
		System.out.println(Thread.currentThread().getName() + ": " +  this.memory);
	}
}
```

**User1.java**
```java
public class User1 extends Thread {	
	private Calculator calculator;
	
	public void setCalculator(Calculator calculator) {
		this.setName("User1");			// 스레드 이름을 User1로 설정
		this.calculator = calculator;	// 공유객체인 Calculator를 필드에 저장
	}
	
	public void run() {
		calculator.setMemory(100);		// 공유객체인 Calculator의 메모리에 100을 저장
	}
}
```

**User2.java**
```java
public class User2 extends Thread {	
	private Calculator calculator;
	
	public void setCalculator(Calculator calculator) {
		this.setName("User2");			// 스레드 이름을 User2로 설정
		this.calculator = calculator;	// 공유객체인 Calculator를 필드에 저장
	}
	
	public void run() {
		calculator.setMemory(50);		// 공유객체인 Calculator의 메모리에 50을 저장
	}
}
```

 동시 작업이 가능한 예제였다. 이런 변질되는 문제점을 해결하기 위한 방법으로 동기화 메소드와 동기화 블록을 활용한다.
   
<br/><br/>
<hr/>

### 교재 593p : 12.4.2 동기화 메소드 및 동기화 블록   
   
 스레드가 사용 중인 객체를 다른 스레드가 변경할 수 없도록 하려면 스레드 작업이 끝날 때까지 객체에 `잠금(lock)`을 걸어서 다른 스레드가 사용할 수 없도록 해야 한다. 멀티 스레드 프로그램에서 단 하나의 스레드만 실행할 수 있는 코드 영역을 `임계 영역(critical section)`이라고 한다. 자바는 임계 영역을 지정하기 위해 `동기화(synchronized)` 메소드와 동기화 블록을 제공한다. 스레드가 객체 내부의 동기화 메소드 또는 블록에 들어가면 즉시 객체에 `잠금`을 걸어 다른 스레드가 임계 영역 코드를 실행하지 못하도록 한다. 동기화 메소드를 만드는 방법은 다은과 같이 메소드 선언에 `synchronized` 키워드를 붙이면 된다. `synchronized` 키워드는 인스턴스와 정적 메소드 어디든 붙일 수 있다.   
   
```java
public synchronized void method() {
	임계 영역	//단 하나의 스레드만 실행
}
```
   
 단순히 메소드 전체를 동기화 하고 싶은 것이 아니라 코드에서 일부분만 동기화를 하고 싶다면 다음과 같이 `동기화 블록`을 이용하면 된다.   
   
```java
public void method() {
// 여러 스레드가 실행 가능 영역
...

synchronized (공유 객체) {		// 동기화 블록
	임계 영역	//단 하나의 스레드만 실행
}

// 여러 스레드가 실행 가능 영역
...
}
```
   
 만약 한 스레드가 동기화된 메소드나 블록에 접근 중이라면 다른 스레드들은 동기화된 임계 영역에 접근이 불가능하다. 단, 일반 메소드는 접근이 가능하다.   
   
![image](https://user-images.githubusercontent.com/84966961/122856593-02a3cb80-d352-11eb-9eea-1177173cc7ed.png)   
   
 다음 코드는 이전 예제인 `Calculaor.java` 파일의 `setMemory()` 메소드를 동기화시켜 준 코드이다.   
   
**Calculator.java**
```java
public class Calculator {
	private int memory;

	public int getMemory() {
		return memory;
	}

	public synchronized void setMemory(int memory) {			// 계산기 메모리에 값을 저장하는 메소드
		this.memory = memory;									// 매개값을 memory 필드에 저장
		try {													// 스레드를 2초간 일시 정지시킴
			Thread.sleep(2000);
		} catch(InterruptedException e) {}	
		System.out.println(Thread.currentThread().getName() + ": " +  this.memory);
	}
}
```
   
 동기화를 진행하는 두번째 방식으로는 블록을 만드는 방식이다. 다음 예제를 보자.
 
 ```java
public class Calculator_block {
	private int memory;

	public int getMemory() {
		return memory;
	}

	public void setMemory(int memory) { 		// 계산기 메모리에 값을 저장하는 메소드
		synchronized (this) {
			this.memory = memory; 				// 매개값을 memory 필드에 저장
			try { 								// 스레드를 2초간 일시 정지시킴
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			System.out.println(Thread.currentThread().getName() + ": " + this.memory);
		}
	}
}
```
 
 두 방식 모두 결과가 똑같이 나오는 것을 알 수 있다. 하지만 메소드에 동기화하는 방법과 달리 블록에 동기화를 해버리면 모든 동기화를 걸은 메소드와 블록이 `잠금(lock)`이 걸리게 된다. 이는 잘못 사용하게 되면 프로그램 처리 속도에 문제를 미칠 수 있는 이유가 된다.
   
 동기화(synchronized)시켜준 코드는 다음과 같이 시간순으로 흐르게 된다.   
   
![image](https://user-images.githubusercontent.com/84966961/122857947-57484600-d354-11eb-88da-e6a28937ad36.png)   
   
 

<br/>
<hr/>

