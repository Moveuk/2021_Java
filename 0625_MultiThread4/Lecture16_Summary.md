# Lecture16 MultiThread4
Key Word : Reentrant, lock(), unlock(), await(), signal(), signalAll(), Condition,     
   
<hr/>
   
## ReentrantLock(재진입 잠금)을 이용한 기존 예제 개선.   
   
 기존 예제에서 Customer와 Cook이 `notify()`하는 대상을 정할 수 없다보니 랜덤적으로 스레드가 `notify()`되어 작업 처리 효율이 떨어지게 되었다. 이런 문제를 해결하기 위해서 `ReentrantLock`을 이용해 잠궈주고 `Condition` 타입 객체를 이용해 `Waiting Pool`을 각각 따로 만들어 주어 작업 처리 효율을 개선하도록 예제를 만들어 보자.   
   
**ReentrantLockTest.java**
```java
package reentrantlock;

public class ReentrantLockTest {

	public static void main(String[] args) throws InterruptedException {
		Table table = new Table();
		
		new Thread(new Cook(table),"COOK1").start();
		new Thread(new Customer(table, "donut"),"CUST1").start();
		new Thread(new Customer(table, "burger"),"CUST2").start();

		Thread.sleep(3000);
		System.exit(0);
	}

}
```
   
**Table.java**
```java
package reentrantlock;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Table {

	String[] dishNames = { "donut", "donut", "burger" };	// 테이블에 올라갈 음식 종류.
	final int MAX_FOOD = 6;		// 최대 음식 갯수.
	public ArrayList<String> dishes = new ArrayList<>();	// 접시에 음식을 담는다.
	
	private ReentrantLock lock = new ReentrantLock(); // 수작업 동기화를 위하여 ReentrantLock을 통해 동기화를 함.
	private Condition forCook = lock.newCondition();	// 요리사와 고객에게 따로 동기화 조건을 걸기위한 조건 객체 생성.
	private Condition forCust = lock.newCondition();
	
	//기존 스레드를 동기화 하기위해 사용하던 3개의 주요 메소드 : wait, notify, notifyAll는
	//ReentrantLock에서는 await(), signal(), signalAll() 메소드와 같다. 
	
	// forCook, forCust는 각각의 Waiting Pool을 만드는 개념이다. 이렇게 되면 선택해서 notify할 수 있다.


	public void add(String dish) {
		lock.lock();	// 
		try {
			while (dishes.size() >= MAX_FOOD) {
				String name = Thread.currentThread().getName();
				System.out.println(name +" is waiting");
			
				try {
					forCook.await();
					Thread.sleep(500);					
				} catch (InterruptedException e) {
				}
			}
			System.out.println("Cook이 " + dish+ "를 만들었습니다.");
			dishes.add(dish);
			forCust.signal();	// notify
			System.out.println("Dishes" + dishes.toString());
		} catch (Exception e) {
		} finally {
			lock.unlock();
		}
		
	}

	public void remove(String dishName) {
		lock.lock();
		String name = Thread.currentThread().getName();
		
		try {
			while (dishes.size() <= 0) {
				System.out.println(name +" is waiting");
				try {
					forCust.await();					
				} catch (InterruptedException e) {
				}
			}
			while (true) {
				for (int i = 0; i < dishes.size(); i++) {
					if (dishName.equals(dishes.get(i))) {
						System.out.println(name+"이 "+dishes.get(i)+"을 먹었습니다.");
						dishes.remove(i);	// 정한 음식 시그널.
						System.out.println("Dishes" + dishes.toString());
						forCook.signal();	// 고객이 먹어서 자리가 비었으니 요리사에게 음식 만들라고 시그널 보냄.
						return;						
					}
				}
				try {
					System.out.println(name +" is waiting");
					forCust.await();
					Thread.sleep(500);
					return;
				} catch (InterruptedException e) {
				}
			}
		} catch (Exception e) {
		} finally {
			lock.unlock();
		}
		
		
	}
	
	public int dishNum() {				// 랜덤 인자를 넣기 위한 음식 종류 갯수 값 리턴 기능.
		return dishNames.length;
	}
}
```
   
**Customer.java**
```java
package reentrantlock;

public class Customer implements Runnable {

	private Table table;
	private String food;
	
	
	public Customer(Table table, String string) {
		super();
		this.table = table;
		this.food = string;
	}

	@Override
	public void run() {
		
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			table.remove(food);
		}
	}

}
```
   
**Cook.java**
```java
package reentrantlock;

public class Cook implements Runnable {
	
	private Table table;

	public Cook(Table table) {
		super();
		this.table = table;
	}
	
	@Override
	public void run() {
		while (true) {
			int idx = (int)(Math.random() * table.dishNum());
			table.add(table.dishNames[idx]);
		}
	}

}
```

### Console 분석   
   
 **Customer가 waiting 하는가?**   
    
 Waiting Pool 이 따로 만들어지므로 waiting 하게 되고 원할때 `signal()`을 보내 풀어주게 된다.   
    
 ![image](https://user-images.githubusercontent.com/84966961/123355983-bea70580-d5a1-11eb-9062-99287cc2ef55.png)




<br/><br/>
<hr/>
   
#### %스레드-안전(THREAD-SAFE)과 재진입가능(REENTRANT)의 차이%
   
 구글링을 하다 관련 글을 찾게 되었다. 병렬 쓰레드 프로그래밍 이해를 위하여 읽어보면 좋을 듯하여 출처 링크를 달아두었다.
    
 http://sjava.net/tag/reentrant/
   
   해당 내용은 위의 출처에서 발췌한 내용이다.   
        
**synchronized 키워드 대신 java.util.concurrent.ReentrantLock 을 썼을 때 이점들   
      
  – lock 을 얻으려는 스레드를 interrupt 시킬 수 있다.   
  – lock 을 얻는데 대한 timeout 을 설정할 수 있다.   
  – lock 에 2개 이상의 condition 을 설정해서 쓸 수 있다.    
  – synchronized 키워드를 위해 JVM 이 사용하는 lock 기능은 하드웨어의 lock 메커니즘과 잘 매핑되지 않는 문제를 피할 수 있다.   
     
**둘 중 무엇을 써야 좋을지에 대한 가이드**   
   
  – java.util.concurrent 패키지에는 lock 기능을 제공하는 다른 클래스들이 많이 있다.   
  – 데이터 구조등의 사용 목적에 따라 이들 클래스를 쓰는 것이 synchronized 키워드나 ReentrantLock 클래스를 쓰는 것보다 낫다.   
  – synchronized 키워드로 잘 동작하고 있는 코드거나 그런 코드를 잘 만들 수 있다면 synchronized 키워드를 쓰는 것이 코드 수도 적고, 에러가 발생할 가능성도 낮다.   
  – 여러 condition 을 설정해서 써야 한다면 ReentrantLock 클래스를 쓴다.   


<br/><br/>
<hr/>
   
## 교재 626p : 12.9 스레드풀    
   
 병렬 작업 처리가 많아지면 스레드 개수가 증가되고 그에 따른 스레드 생성과 스케줄링으로 인해 CPU가 바빠져 메모리 사용량이 늘어난다. 따라서 애플리케이션의 성능이 저하된다. 갑작스런 병렬 작업의 폭증으로 인한 스레드의 폭증을 막으려면 스레드 풀을 사용해야 한다. 스레드풀은 작업 처리에 사용되는 스레드를 제한된 개수만큼 정해 놓고 작업 큐에 들어오는 작업들을 하나씩 스레드가 맡아 처리한다. 작업 처리가 끝난 스레드는 다시 작업 큐에서 새로운 작업을 가져와 처리한다. 그렇기 때문에 작업 처리 요청이 폭증 되어도 스레드의 전체 개수가 늘어나지 않으므로 애플리케이션 성능이 급격히 저하되지 않는다.   
    
자바는 스레드 풀을 생성하고 사용할 수 있도록 `ava.util.concurrent`패키지에서 `xcutorService 인터페이스`와 `Excutors 클래스`를 제공하고 있다. Excutors의 다양한 정적 메소드를 이용해서 ExcutorService 구현 객체를 만들 수 있는데, 이것이 바로 스레드 풀이다. 아래 그림은 ExcutorService가 동작하는 방식을 보여준다.   
   
![image](https://user-images.githubusercontent.com/84966961/123356577-ed71ab80-d5a2-11eb-89a3-a9d8a5c80f9d.png)    
출처 : https://velog.io/@sa1341/%EB%A9%80%ED%8B%B0-%EC%8A%A4%EB%A0%88%EB%93%9C%EC%9D%98-%EC%83%81%ED%83%9C-%EC%A0%9C%EC%96%B4

   
<br/><br/>
<hr/>
   
### 교재 627p : 12.9.1 스레드풀 생성 및 종료    
   
<br/>
   
#### 스레드풀 생성   
   
 ExcutorService 구현 객체는 Excutors 클래스의 다음 두 가지 메소드 중 하나를 이용해서 간편하게 생성할 수 있다.   
   
| 메소드명(매개 변수) | 초기 스레드 수 | 코어 스레드 수 | 최대 스레드 수 |
|---|---|---|---|
| newCachedThreadPool() | 0 | 0 | Integer.MAX_VALUE |
| newFixedThreadPool(int nThreads) | 0 | nThreads | nThreads |
<br/><br/>
   
 **위 테이블 속성 설명**   
   
 초기 스레드 수 : ExcutorService 객체가 생성될 때 기본적으로 생성되는 스레드 수
 코어 스레드 수 : 스레드 수가 증가된 후 사용되지 않는 스레드를 스레드 풀에서 제거할 때 최소한 유지해야 할 스레드 수
 최대 스레드 수 : 스레드 풀에서 관리하는 최대 스레드 수
   
 `newCachedThreadPool() 메소드`로 생성된 스레드 풀의 특징은 초기 스레드 개수와 코어 스레드 개수는 0이고, 스레드 개수보다 작업 개수가 많으면 새 스레드를 생성시켜 작업을 처리한다. 이론적으로 int 값이 가질 수 있는 최대값 만큼 스레드가 추가되지만, 운영체제의 성능과 상황에 따라 달라진다. 1개 이상의 스레드가 추가되었을 경우 **60초동안** 추가된 스레드가 아무런 작업을 하지 않으면 **스레드를 종료**하고 **풀에서 제거**한다.    
<br/><br/>
**newCachedThreadPool()을 호출해서 ExecutorService 객체 구현**   

```java
ExecutorService excutorService = Executors.newCachedThreadPool();
```

 `newFixedThreadPool(int nThreads) 메소드`로 생성된 스레드풀의 초기 스레드 개수는 0개이고, 코어 스레드 수는 nThreads 이다. 스레드 개수보다 작업 개수가 많으면 새 스레드를 생성시키고 작업을 처리한다. 최대 스레드 개수는 매개값으로 준 nThreads 이다. 이 스레드풀은 스레드가 작업을 처리하지 않고 놀고 있더라도 스레드 개수가 줄지 않는다. CPU 코어의 수 만큼 최대 스레드를 사용하는 스레드 풀을 생성한다.    
<br/><br/>
**newFixedThreadPool()을 호출해서 ExecutorService 객체 구현**    
```java
ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
```
    
#### `newCachedThreadPool()`과 `newFixedThreadPool()`를 사용하지 않고 스레드 설정 방법.
	
 `newCachedThreadPool()`과 `newFixedThreadPool()` 메소드를 사용하지 않고 코어 스레드 개수와 최대 스레드 개수를 설정하고 싶다면 직접 ThreadPoolExecutor 객체를 생성하면 된다. 사실 위 두가지 메소드 내부적으로 ThreadPoolExecutor 객체를 생성해서 리턴한다.    
    
 다음 코드는 초기 스레드 개수가 0개, 코어 스레드 개수가 3개, 최대 스레드 개수가 100개인 스레드 풀을 생성한다. 그리고 코어 스레드 3개를 제외한 나머지 추가된 스레드가 120초 동안 놀고 있을 경우 해당 스레드를 제거해서 스레드 수를 관리한다.   
   
```java
ExecutorService threadPool = new ThreadPoolExecutor(
    3,    //코어 스레드 개수
    100,  //최대 스레드 개수
    120L, //놀고 있는 시간
    TimeUnit.SECONDS, // 놀고 있는 시간 단위
    new SynchronousQueue<Runnable>() // 작업 큐 
);
```

<br/>
<hr/>

#### 스레드풀 종료   
   
 스레드풀의 스레드는 기본적으로 데몬 스레드가 아니기 때문에 **main 스레드가 종료되더라도 작업을 처리하기 위해 계속 실행되는 상태로 남아있다.** 그래서 `main()` 메소드가 실행이 끝나도 애플리케이션 프로세스는 종료되지 않는다. 애플리케이션을 종료하려면 스레드풀을 종료시켜 스레드들이 종료상태가 되도록 처리해줘야 한다. ExecutorService는 종료와 관련해서 다음 세 개의 메소드를 제공하고 있다.   
    
| 리턴타입 | 메소드(매개변수) | 설명 |
|---|---|---|
| void | shutdown() | 현재 처리 중인 작업뿐만 아니라 작업 큐에 대기하고 있는 모든 작업을 처리한 뒤에 스레드풀을 종료시킨다. | 
| List<Runnable> | shutdownNow() | 현재 작업 처리 중인 스레드를 interrupt해서 작업 중지를 시도하고 스레드풀을 종료시킨다. 리턴값은 작업 큐에 있는 미처리된 작업(Runnable)의 목록이다. |
| boolean	awaitTermination(long timeout, TimeUnit unit) | shutdown() 메소드 호출 이후, 모든 작업 처리를 timeout 시간 내에 완료하면 true를 리턴하고, 완료하지 못하면 작업 처리 중인 스레드를 interrupt하고 false를 리턴한다. |
   
 남아있는 작업을 마무리하고 스레드 풀을 종료할 때에는 shutdown()을 일반적으로 호출하고, 남아있는 작업과는 상관없이 **강제로 종료**할 때는 `shotdownNow()`를 호출한다.    
   
```java
executorService.shutdown();
또는
executorService.shutdownNow();
```
   
<br/><br/>
<hr/>
   
### 교재 629p : 12.9.2 작업 생성과 처리 요청  
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
   
<br/><br/>
<hr/>
   




