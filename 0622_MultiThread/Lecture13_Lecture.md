# 멀티 스레드 (MultiThread)


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
    
 동시성
   
<br/><br/>
<hr/>

## 교재 588p : 12.3 스레드 우선순위   
   
 동시성(Concurrency) 또는 병렬성(Parallelism)   
    
![image](https://user-images.githubusercontent.com/84966961/122850682-a4261f80-d348-11eb-8808-9e0fe8414c09.png) ![image](https://user-images.githubusercontent.com/84966961/122850688-a7b9a680-d348-11eb-94f3-bc860494c18c.png)












