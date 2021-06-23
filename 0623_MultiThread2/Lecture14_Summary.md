# 멀티 스레드의 상태 제어    
Key Word :    
   
<hr/>
   
## 교재 597p : 12.5 스레드 상태   
   
 스레드는 객체를 생성하고, start() 메소드를 호출하면 곧바로 스레드가 실행되는 것처럼 보이지만 실은 실행 대기 상태가 된다. 실행 대기 상태란 아직 스케줄링이 되지 않아서 실행을 기다리고 있는 상태를 말한다. 실행 대기 상태에 있는 스레드 중에서 스레드 스케줄링으로 선택된 스레드가 비로서 CPU를 점유하고 run() 메소드를 실행한다.   
   
이때를 `실행(Running)`상태라고 한다. 실행 상태의 스레드는 run() 메소드를 모두 실행하기 전에 스레드 스케줄링에 의해 다시 실행 대기 상태로 돌아 갈 수 있다. 그리고 실행 대기 상태에 있는 다른 스레드가 선택되어 실행 상태가 된다. 이렇게 스레드는 실행 대기 상태와 실행 상태를 번갈아가면서 자신의 run() 메소드를 조금씩 실행한다. 실행 상태에서 run() 메소드가 종료되면, 더 이상 실행할 코드가 없기 때문에 실행은 멈추게 된다. 이 상태를 종료 상태라고 한다.   
   
**스레드의 일반적인 실행 상태**   
![image](https://user-images.githubusercontent.com/84966961/123015476-781ba480-d403-11eb-8d1e-6bd1c5725117.png)     
   
경우에 따라서 스레드는 실행 상태에서 실행 대기 상태로 가지 않을 수도 있다. 실행 상태에서 일시 정지 상태로 가기도 하는데, 일시 정지 상태는 스레드가 실행할 수 없는 상태이다. 일시 정지 상태는 WAITINGM, TIMED_WATING, BLOCKED가 있다. 스레드가 다시 실행 상태로 가기 위해서는 일시 정지 상태에서 실행 대기 상태로 가야 한다는것을 알아둬야 한다.   
   
   
**스레드의 상태와 일시 정지 상태**   
![image](https://user-images.githubusercontent.com/84966961/123015520-8a95de00-d403-11eb-9144-004c36a80fc3.png)   
   
이러한 스레드의 상탱를 코드에서 확인할 수 있도록 하기 위해 자바 5부터 Thread 클래스에 getState() 메소드가 추가 되었다. getState() 메소드는 아래 표처럼 스레드 상태에 따라서 Thread.State 열거 상수를 리턴한다.   
   
![image](https://user-images.githubusercontent.com/84966961/123015606-b87b2280-d403-11eb-9c59-dc76b4c713a5.png)   
   
**스레드의 생명 주기**    
![image](https://user-images.githubusercontent.com/84966961/123027303-90e28500-d418-11eb-8426-ada4b21a6c9e.png)   
   

   
<br/><br/>
<hr/>
   
## 교재 600p : 12.6 스레드 상태 제어  
   
사용자는 미디어 플레이어에서 동영상을 보다가 일시 정지시킬 수도 있고, 종료시킬 수도 있다. 일시 정지는 조금 후 다시 동영상을 보겠다는 의미로 미디어 플레이어는 동영상 스레드를 일시 정지 상태로 만들어야 한다. 그리고 종료는 더 이상 동영상을 보지 않겠다는 의미이므로 미디어 플레리어는 스레드를 종료 상태로 만들어야 한다. 이와 같이 실행 중인 스레드의 상태를 변경하는 것을 스레드 상태 제어라고 한다.   

멀티 스레드 프로그램을 만들기 위해서는 정교한 스레드 상태 제어가 필요한데, 상태 제어가 잘못되면 프로그램은 불안정해져서 먹통이 되거나 다운된다. 멀티 스레드 프로그래밍이 어렵다고 하는 이유는 여기에 있다. 스레드는 잘 사용하면 약이 되지만, 잘못 사용하면 치명적인 프로그램의 버그가 되기 때문에 스레드를 정확하게 제어하는 방법을 잘 알고 있어야 한다. 스레드 제어를 제대로 하기 위해서는 스레드의 상태 변화를 가져오는 메소드를 파악하고 있어야 한다. 아래 그림은 상태 변화를 가져오는 메소드의 종류를 보여준다.     
   
**스레드의 상태 변화**
![image](https://user-images.githubusercontent.com/84966961/123016157-f167c700-d404-11eb-8f35-2380ec3d72fb.png)
`%위 그림에서 취소선을 가진 메소드는 스레드의 안전성을 해친다고 하여 더 이상 사용하지 않도록 권장된 Deprecated 메소드들이다.`

**수업 설명**
![image](https://user-images.githubusercontent.com/84966961/123016391-7652e080-d405-11eb-9394-7078d650b898.png)   
   
**스레드 상태 제어 메소드**   
![image](https://user-images.githubusercontent.com/84966961/123016982-b8305680-d406-11eb-9649-5f105e4dca93.png)   
   
 `위에서 설명한 wait(), notify(), notifyAll()은 Object 클래스의 메소드이고, 그 이외의 메소드는 모두 Thread 클래스의 메소드들이다.`   
   
<br/><br/>
<hr/>
   
### 교재 602p : 12.6.1 주어진 시간동안 일시 정지(sleep())     
   
실행 중인 스레들를 일정 시간 멈추게 하고 싶다면 Thread 클래스의 정적 메소드인 sleep()을 사용하면 된다. 다음과 같이 Thread.sleep() 메소드를 호출한 스레드는 주어진 시간 동안 일시 정지 상태가 되고, 다시 실행 대기 상태로 돌아간다.   

```java
try{
    Thread.sleep(1000);
}catch(Exception e){
    // interrupt() 메소드가 호출되면 실행
}
```
 일전에 예제들처럼 스레드를 재운 것.   
   
 매개값에는 얼마 동안 일시 정지 상태로 있을 것인지, 밀리세컨드(1/1000) 단위로 시간을 주면 된다. 위와 같이 1000이라는 값을 주면 스레드는 1초가 경과할 동안 일시 정지 상태로 있게 된다. 일시 정지 상태로 주어진 시간이 되기 전에 interrupt() 메소드가 호출되면 InterruptedException이 발생하기 때문에 예외 처리가 필요하다.   
    
 일전에 실습해보았던 비프음 예제를 다시 읽어보며 슬립의 의미를 기억해보자.   
   
<br/><br/>
<hr/>
   
### 교재 603p : 12.6.2 다른 스레드에게 실행 양보(yield())     
   
스레드가 처리하는 작업은 반복적인 실행을 위해 for문이나 while문을 포함하는 경우가 많다. 가끔은 이 반복문들이 무의미한 반복을 하는 경우가 있다.   
   
```java
public void run(){
    while(true){
        if(work){
            System.out.println("ThreadA 작업 내용");
        }
    }
}
```
    
스레드가 시작되어 run() 메소드를 실행하면 while(true) {} 블록을 무한 반복 실행한다. 만약 work 값이 false라면 그리고 work의 값이 false에서 true로 변경되는 시점이 불명확하다면, while 문은 어떠한 실행문도 실행하지 않고 무의미한 반복을 한다.(while 문에는 사실상 아무 것도 없이 존재하는 이유, 단순히 스레드만 잡아먹고 있음.) 이것보다는 다른 스레드에게 실행을 양보하고 자신은 실행 대기 상태로 가는 것이 전체 프로그램 성능에 도움이 된다. 이런 기능을 위해서 스레드는 yield() 메소드를 제공한다. yield() 메소드를 호출한 스레드는 실행 대기 상태로 돌아가고 동일한 우선순위 또는 높은 우선순위를 갖는 다른 스레드가 실행 기회를 가질 수 있도록 해준다.   
   
![image](https://user-images.githubusercontent.com/84966961/123017321-53293080-d407-11eb-9d11-e36a931d94d4.png)
   
 다음 코드는 의미 없는 반복을 줄이기 위해서 else에 `thread.yield()`를 주어 스레드를 멈추고 권한을 넘기도록 수정한 것이다.   

```java
public void run(){
    while(true){
        if(work){
            System.out.println("ThreadA 작업 내용");
        } else {
            Thread.yield();
        }
    }
}
```

다음 예제는 처음에 A,B가 동시 실행되고 3초간 메인 스레드를 정지시켜 A,B가 출력되도록 한다. 하지만 3초 이후 A를 정지시켜서 다시 3초 동안 B만 돌리고 다시 A를 실행하고 난 후  A와 B가 돌자마자 메인함수가 빠르게 끝나 버린다. 이 예제를 보면 스레드는 총 3개 메인, A, B가 돌아가고 있는 것을 알 수 있다.

**604p ~ 605p 스레드 실행 양보 예제**   
   
**YieldExample.java**
```java
public class YieldExample {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();

        // ThreadA, ThreadB 모두 실행
        threadA.start();
        threadB.start();

        try{
            Thread.sleep(3000);				// 메인 함수를 슬립하는 동안 A,B가 서로 작동함.
//          ThreadA.sleep(3000);			// 슬립은 해당 스레드만 재울 수 있다.메인에서 A 못재움.
        } catch (InterruptedException e) {}

        // ThreadB만 실행
        threadA.work = false;

        try{
            Thread.sleep(3000);				// 위에서 A를 꺼버려서 3초간 B만 실행.
        }catch (InterruptedException e) {}

        // ThreadA, ThreadB 모두 실행
        threadA.work = true;

        // ThreadA, ThreadB 모두 종료
        threadA.stop = true;
        threadB.stop = true;
    }
}
```

**ThreadA.java**
```java
public class ThreadA extends Thread {
    public boolean stop = false; 		// 종료 플래그
    public boolean work = true; 		// 작업 진행 여부 플래그

    @Override
    public void run() {
        while (!stop) { 				// stop이 true가 되면 while 문 종료
            if (work) {
                System.out.println("ThreadA 작업 내용");
            } else {
                Thread.yield(); 		// work가 false가 되면 다른 스레드에게 실행 양보
            }
        }

        System.out.println(this.getName() + "종료");
    }
}
```

**ThreadB.java**
```java
public class ThreadB extends Thread {
    public boolean stop = false; 			// 종료 플래그
    public boolean work = true; 			// 작업 진행 여부 플래그

    @Override
    public void run() {
        while (!stop) { 					// stop이 true가 되면 while 문 종료
            if (work) {
                System.out.println("ThreadB 작업 내용");
            } else {
                Thread.yield(); 			// work가 false가 되면 다른 스레드에게 실행 양보
            }
        }

        System.out.println(this.getName() + "종료");
    }
}
```

**%중요 사항%**
   
 `sleep은 해당 스레드에서만 재워야한다.`


   
<br/><br/>
<hr/>
   
### 교재 606p : 12.6.3 다른 스레드의 종료를 기다림(join())     
   
스레드는 다른 스레드와 독립적으로 실행하는 것이 기본이지만 다른 스레드가 종료될 때까지 기다렸다가 실행해야 하는 경우가 발생할 수도 있다. 예를 들어 계산 작업을 하는 스레드가 모든 계산 작업을 마쳤을 때, 계산 결과값을 받아 이용하는 경우가 이에 해당한다. 이런 경우를 위해서 Thread는 join() 메서드를 제공하고 있다. 아래 그림을 보고 이해하면 된다. ThreadA가 ThreadB의 join() 메소드를 호출하면 ThreadA는 ThreadB가 종료할 때까지 일시 정지 상태가 된다. ThreadB의 run() 메소드가 종료되면 비로소 ThreadA는 일시 정지에서 풀려 다음 코드를 실행하게 된다.   
   
![image](https://user-images.githubusercontent.com/84966961/123022094-380eee80-d410-11eb-9600-98006f7ca4ef.png)
    
 다음 예제를 통해 메인 스레드가 하위 스레드가 종료될 때까지 일시 정지 상태에 있는 것을 알 수 있다.   
   
**SumThread.java**
```java
public class SumThread extends Thread {	
	private long sum;
	
	public long getSum() {
		return sum;
	}

	public void setSum(long sum) {
		this.sum = sum;
	}

	public void run() {
		for(int i=1; i<=100; i++) {
			sum+=i;
		}
	}
}
```
   
**JoinExample.java**
```java
public class JoinExample {
	public static void main(String[] args) {
		SumThread sumThread = new SumThread();
		sumThread.start();
		try {
			sumThread.join();			// SumThread가 끝날 때까지 기다려줌.
		} catch (InterruptedException e) {
		}
		System.out.println("1~100 합: " + sumThread.getSum());
	}
}
```

 `join()` 이 있기 때문에 1~100 까지의 합의 결과가 나온다. 만약 다음과 같이 `join()`을 빼게 되면 메인이 `SumThread`가 실행되고 얼마 후에 바로 종료되어 결과가 0이 나오는 것을 알 수 있다.   
   
```java
public class JoinExample {
	public static void main(String[] args) {
		SumThread sumThread = new SumThread();
		sumThread.start();
//		try {							// join() 주석처리
//			sumThread.join();			
//		} catch (InterruptedException e) {
//		}
		System.out.println("1~100 합: " + sumThread.getSum());
	}
}
```

**결과 화면**   
   
![image](https://user-images.githubusercontent.com/84966961/123022771-3c87d700-d411-11eb-8921-b4f7ad64d996.png)
   



   
<br/><br/>
<hr/>
   
### 교재 608p : 12.6.4 스레드 간 협업(wait(), notify(), notifyAll())     
   
 경우에 따라서는 두개의 스레드를 교대로 번갈아가며 실행해야 할 경우가 있다. 정확한 교대 작업이 필요할 경우, 자신의 작업이 끝나면 상대방 스레드를 일시 정지 상태에서 풀어주고, 자신은 일시 정지 상태로 만드는 것이다. 이 방법의 핵심은 공유 객체에 있다. 공유 객체는 두 스레드가 작업할 내용을 각각 동기화 메소드로 구분해 놓는다. 한 스레드가 작업을 완료하면 `notify()` 메소드를 호출해서 일시 정지 상태에 있는 다른 스레드를 실행 대기 상태로 만들고, 자신은 두 번 작업을 하지 않도록 wait() 메소드를 호출하여 일시 정지 상태로 만든다.   
   
![image](https://user-images.githubusercontent.com/84966961/123022919-7c4ebe80-d411-11eb-9cdc-113bd5587b1f.png)
   
 만약 `wait()` 대신 `wait(long timeout)`이나, `wait(long timeoutm, int nanos)`를 사용하면 `notify()`를 호출하지 않아도 지정된 시간이 지나면 스레드가 **자동적으로 실행 대기 상태**가 된다. notify() 메소드와 동일한 역할을 하는 `notifyAll()` 메소드도 있는데, `notify()`는 `wait()`에 의해 일시 정지된 모든 스레드들을 실행 대기 상태로 만든다. 이 메소드들은 Thread 클래스가 아닌 Object 클래스에 선언된 메소드이므로 모든 공유 객체에서 호출이 가능하다. **주의할 점**은 이 메소드들은 **동기화 메소드** 또는 **동기화 블록** 내에서만 사용할 수 있다.   
    
 다음 예제는 두 스레드 작업을 WorkObject의 `methodA()`와 `methodB()`에 정의해 두고, 두 스레드 ThreadA와 ThreadB가 교대로 `methodA()`와 `methodB()`를 호출하도록 했다.   
   
![image](https://user-images.githubusercontent.com/84966961/123025070-08aeb080-d415-11eb-875d-838bd69d6d7d.png)   
   
**WorkObject.java**
```java
public class WorkObject {
	public synchronized void methodA() {
		System.out.println("ThreadA의 methodA() 작업 실행");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
		}
	}
	
	public synchronized void methodB() {
		System.out.println("ThreadB의 methodB() 작업 실행");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
		}
	}
}
```


**ThreadA.java**
```java
public class ThreadA extends Thread {
	private WorkObject workObject;

	public ThreadA(WorkObject workObject) {
		this.workObject = workObject;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			workObject.methodA();
		}
	}
}
```


**ThreadB.java**
```java
public class ThreadB extends Thread {
	private WorkObject workObject;

	public ThreadB(WorkObject workObject) {
		this.workObject = workObject;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			workObject.methodB();
		}
	}
}

```


**WaitNotifyExample.java**
```java
public class WaitNotifyExample {
	public static void main(String[] args) {
		WorkObject sharedObject = new WorkObject();
		
		ThreadA threadA = new ThreadA(sharedObject);
		ThreadB threadB = new ThreadB(sharedObject);
		
		threadA.start();
		threadB.start();
	}
}
```

#### 실행 순서

1. `WaitNotifyExample.java`의 `main`이 실행되어 thread A,B 객체가 생성되고 A가 먼저 실행됨.   
2. thread A로 건너간 다음 `WorkObject` 객체를 생성하고 `workObject.methodA ()`를 호출함.   
3. `WorkObject.java`에서 for문 안의`methodA()`가 실행되고 프린트됨. 이 후 Wating Pool에 존재하는 스레드들에게 실행 대기 상태로 대기하라는 명령을 내림(`notify()`). 다만, 첫 동작시에는 아무도 없기 때문에 명령이 무시됨.   
4. 이 후 `wait()`가 실행되어 Thread A가 Waiting Pool로 들어감. 여기까지 메인의 10번째 줄 코드 `threadA.start();` 종료.   
5. 다시 `WaitNotifyExample.java`로 돌아와 11번째 줄 코드인 `threadB.start();`가 실행됨.   
6. threadA와 같은 방식으로 진행되고 스레드 내부의 for문이 끝날 때까지 A,B를 반복함.   

**결과 화면**   
   
![image](https://user-images.githubusercontent.com/84966961/123026534-51ffff80-d417-11eb-894b-380c8cc10466.png)   
   
 10번 반복.

**%`notify()`와 `notifyAll()` 차이점%**
 `notify()`는 Waiting Pool 에 있는 한 개의 스레드를 실행 대기 상태로 명령하고 `notifyAll()` 은 Waiting Pool 에 있는 모든 스레드를 풀어준다.   
   
   
<br/><br/>
<hr/>
   
### 교재 611p : Wait()와 Notify() 심화   
   
 이제는 Wait()와 Notify()를 통해 2개의 스레드가 협동하여 작업을 처리하는 예제를 볼 것이다.   
   
![image](https://user-images.githubusercontent.com/84966961/123027610-1403db00-d419-11eb-93bf-9ca4cd7a7711.png)   
   
**생산자와 소비자의 협동 과정**
 1. 위 그림을 보면 1번 소비자 스레드가 wait()를 호출하여 사용할 데이터가 생성될 때까지 기다린다.
 2. 그 다음 생산자 스레드가 데이터를 저장한다.
 3. 데이터를 저장한 후 소비자 스레드에게 작업이 준비되었음을 notify()를 호출하여 알린다.
 4. 생산자 스레드의 업무가 종료되어 wait() 상태로 들어간다. (Waiting Pool로 들어간다.)
 5. 공유 객체에 저장된 데이터를 소비자 스레드가 찾아 사용한다.

#### 두 스레드의 작업 내용을 동기화 메소드로 작성한 공유 객체 예제

**DataBox.java**
```java
public class DataBox {
	private String data;
	
	public synchronized String getData() {
		if(this.data == null) {				// 가져갈 데이터가 없으면 만들어 질 때까지 웨이팅으로 만듬.
			try {
				wait();
			} catch(InterruptedException e) {}
		}
		String returnValue = data;
		System.out.println("ConsummerThread가 읽은 데이터: " + returnValue);
		data = null;
		notify();
		return returnValue;
	}
	
	public synchronized void setData(String data) {
		if(this.data != null) {				// 데이터가 있으면 빼갈 때까지 웨이팅으로 만듬.
			try {
				wait();
			} catch(InterruptedException e) {}
		}
		this.data = data;
		System.out.println("ProducerThread가 생성한 데이터: " + data);
		notify();
	}
}
```
  
**ProducerThread.java**
```java
public class ProducerThread extends Thread {
	private DataBox dataBox;

	public ProducerThread(DataBox dataBox) {
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=3; i++) {
			String data = "Data-" + i;
			dataBox.setData(data);
		}
	}
}
```


**ConsumerThread.java**
```java
public class ConsumerThread extends Thread {
	private DataBox dataBox;

	public ConsumerThread(DataBox dataBox) {
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=3; i++) {
			String data = dataBox.getData();
		}
	}
}
```


**WaitNotifyExample.java**
```java
public class WaitNotifyExample {
	public static void main(String[] args) {
		DataBox dataBox = new DataBox();
		
		ProducerThread producerThread = new ProducerThread(dataBox);
		ConsumerThread consumerThread = new ConsumerThread(dataBox);
		
		producerThread.start();
		consumerThread.start();
	}
}
```
   
 쉽게 보면 생성자가 데이터 만들면 소비자가 사용한다는 것이다.

<br/><br/><br/><br/>   
   
#### 수업 중 생각난 yield()와 wait()
   
 생각난 질문 : 그렇다면 wait()는 멈출 때 스레드를 붙잡고 있어서 작업 효율을 떨어트리는가?
 구글링 답안 : 
 `wait()`가 호출되면 쓰레드는 이때까지 자기가 걸고있던 모든 락을 다 풀고, **WAITING 상태로 전환**되면서 `wait()`이 호출된 객체의 대기실에서 대기하게 된다.   
 `sleep()`의 경우에도 `WAITING 상태로 전환`된다고 설명에 나와있다.   
 따라서, 기본적으로 `wait()`는 `yield()` 기능이 탑재되어 있는 것은 아니다. `notify()` 로 풀어주고 모든 실행이 끝날 때까지 스레드를 붙잡고 있을 것이다. 아마도...   
   
**yield**
RUNNABLE 상태로 들어가면서 다른 쓰레드에게 작업을 양보하는 메서드이다.   
이 또한 static 메서드로 제공되며, 이 메서드를 호출한 쓰레드가 RUNNABLE 상태로 들어가게 된다.   
   
`다른 메서드들과의 차이점은 WAITING 상태로 들어가지 않고 바로 RUNNABLE 로 들어간다는 점이다`   
   
   
<br/><br/>
<hr/>
   
## Quiz   
   
**Account.java**
```java
public class Account {
	private int balance = 1000;
	
	public int getBalance() {
		return balance;
	}
	
	public synchronized void withdraw(int money) {
		if (balance >= money) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			balance -= money;
		}
	}
}
```

   
**RunnableEx.java**
```java
public class RunnableEx implements Runnable {

	Account acc = new Account();
	
	@Override
	public void run() {

		while (acc.getBalance() > 0) {
			int money = (int)(Math.random() * 3 + 1) * 100;
			acc.withdraw(money);
			System.out.println("balance" + acc.getBalance());
			System.out.println(Thread.currentThread());		// 현재 쓰레드 참조 코드
		}

	}

}
```

   
**AccountTest.java**
```java
public class AccountTest {

	public static void main(String[] args) {

		RunnableEx r = new RunnableEx();
		Thread p1 = new Thread(r);
		Thread p2 = new Thread(r);
		String name2 = p2.getName();
		
		p1.start();
		p2.start();

	}

}
```

**결과 화면**   
   
![image](https://user-images.githubusercontent.com/84966961/123040782-47516480-d42f-11eb-8ebf-7fd3a2d2c5fc.png)















