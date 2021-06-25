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

<br/><br/>

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

<br/><br/>
<hr/>

#### 스레드풀 종료   
   
 스레드풀의 스레드는 기본적으로 데몬 스레드가 아니기 때문에 **main 스레드가 종료되더라도 작업을 처리하기 위해 계속 실행되는 상태로 남아있다.** 그래서 `main()` 메소드가 실행이 끝나도 애플리케이션 프로세스는 종료되지 않는다. 애플리케이션을 종료하려면 스레드풀을 종료시켜 스레드들이 종료상태가 되도록 처리해줘야 한다. ExecutorService는 종료와 관련해서 다음 세 개의 메소드를 제공하고 있다.   
    
| 리턴타입 | 메소드(매개변수) | 설명 |
|---|---|---|
| void | shutdown() | 현재 처리 중인 작업뿐만 아니라 작업 큐에 대기하고 있는 모든 작업을 처리한 뒤에 스레드풀을 종료시킨다. | 
| List<Runnable> | shutdownNow() | 현재 작업 처리 중인 스레드를 interrupt해서 작업 중지를 시도하고 스레드풀을 종료시킨다. 리턴값은 작업 큐에 있는 미처리된 작업(Runnable)의 목록이다. |
| boolean | awaitTermination(long timeout, TimeUnit unit) | shutdown() 메소드 호출 이후, 모든 작업 처리를 timeout 시간 내에 완료하면 true를 리턴하고, 완료하지 못하면 작업 처리 중인 스레드를 interrupt하고 false를 리턴한다. |
   
 남아있는 작업을 마무리하고 스레드 풀을 종료할 때에는 shutdown()을 일반적으로 호출하고, 남아있는 작업과는 상관없이 **강제로 종료**할 때는 `shotdownNow()`를 호출한다.    
   
```java
executorService.shutdown();
또는
executorService.shutdownNow();
```
   
<br/><br/>
<hr/>
   
### 교재 629p : 12.9.2 작업 생성과 처리 요청  
   
#### 작업 생성

하나의 작업은 Runnable 또는 Callable 구현 클래스로 표현한다. Runnable과 Callable의 차이점은 작업 처리 완료 후 리턴값이 있느냐 없느냐 다. 다음은 작업을 정의하기 위해 Runnable과 Callable을 구현 클래스를 작성하는 방법을 보여준다.
	
```java
// Runnable 구현 클래스
Runnable task = new Runnable(){
    @Override
    public void run(){
        //스레드가 처리할 작업 내용
    }
}
//Callable 구현 클래스
Callable<T> task = new Callable<T>(){

    @Override
    public T call() throws Exception{
        //스레드가 처리할 작업 내용
        return T;
    }
}	
```

 Runnable의 run() 메소드는 리턴값이 없고, Callable의 `call()` 메소드는 리턴 값이 있다. `call()` 의 리턴 타입은 implements Callable에서 지정한 T타입이다. 스레드풀의 스레드는 작업 큐에서 Runnable 또는 Callable 객체를 가져와 `run()`과 `call()` 메소드를 실행한다.   
   
<br/><br/>
   
#### 작업 생성
	
 `작업 처리 요청`이란 ExecutorService의 작업 큐에 Runnable 또는 Callable 객체를 넣는 행위를 말한다. ExecutorService는 작업 처리 요청을 위해 다음 두가지 종류의 메소드를 제공한다.   
	

|리턴타입|메소드(매개변수)|설명|
|---|---|---|
|void|execute(Runnable command)|Runnalbe을 작업 큐에 저장, 작업 처리 결과를 받지 못함|
|Future<?>, Future<V>, Future<V>|submit(Runnable task), submit(Runnable task, V result), submit(Callable\ task)|Runnable 또는 Callable을 작업 큐에 저장, 리턴된 Future를 통해 작업 처리 결과를 얻을 수 있음|
	
	<br/><br/>
 **`execute()`와 `submit()` 메소드의 차이점의 두 가지**
하나는 execute()는 작업 처리 결과를 받지 못하고 submit()은 작업 처리 결과를 받을 수 있도록 Future를 리턴한니다. 또 다른 차이점은 execute()는 작업 처리 도중 예외가 발생하면 스레드가 종료되고 해당 스레드는 스레드풀에서 제거된다. 따라서 스레드풀은 다른 작업 처리를 위해 새로운 스레드를 생성한다. 반면에 submit()은 작업 처리 도중 예외가 발생하더라도 스레드는 종료되지 않고 다음 작업을 위해 재사용된다. 그렇기 때문에 가급적이면 스레드의 생성 오버헤더를 줄이기 위해서 submit()을 사용하는 것이 좋다.   
   
<br/><br/>
#### execute()와 submit() 메소드로 각각 처리 요청했을 경우 예제   
   
 다음 예제는 Runnable 작업을 정의할 때 Intefer.parseInt("삼")을 넣어 NumberFormatException이 발생하도록 유도한 것이다. 10개 작업을 `execute()`와 `submit()` 메소드로 각각 처리 요청했을 경우 스레드풀의 상태를 보자.

```java
public class ExecuteExample {
    public static void main(String[] args) throws Exception {
        
        ExecutorService executorService = Executors.newFixedThreadPool(2); // 최대 스레드 개수가 2인 스레드풀 생성
        // 총 10개 스레드 만들지만 풀에서는 2개까지 작업 처리가능.
        System.out.println(Runtime.getRuntime().availableProcessors());	// 사용가능한 프로세서 수 보여주는 건듯.
        
        for (int i = 0; i < 10; i++) {	// 스레드 10개 발생.
        	// 작업 정의
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // 스레드 총 개수 및 작업 스레드 이름 출력
                    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
                    int poolSize = threadPoolExecutor.getPoolSize();
                    String threadName = Thread.currentThread().getName();
                    System.out.println("[총 스레드 개수: " + poolSize + "] 작업 스레드 이름: " + threadName);

                    // 예외 발생 시킴
                    int value = Integer.parseInt("삼");
                }
            };
            
//            executorService.execute(runnable); // 작업 처리요청. 작업 처리 결과 받지 못함
            executorService.submit(runnable);	// 작업 처리요청. 리턴된 Future를 통해 작업 처리 결과를 얻을 수 있음.
            
            
            
            Thread.sleep(100);	// 콘솔에 출력 시간을 주기 위해 0.01초 일시 정지 시킴
        }

        executorService.shutdown();				// 스레드 풀 종료
    }
}
```
**execute 결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/123361784-9bcd1f00-d5aa-11eb-9b7b-25f70e7674fb.png)
   
**submit 결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/123362478-ba7fe580-d5ab-11eb-8b86-c48b31f6d753.png)


   
<br/><br/>
<hr/>
   
### 교재 632p : 12.9.3 블로킹 방식의 작업 완료 통보     
    
 ExecutorService의 `submit()` 메소드는 매개값으로 준 **Runnable 또는 Callable 작업**을 스레드풀의 작업 큐에 저장하고 즉시 Future 객체를 리턴한다.   
   

| 리턴타입 | 메소드(매개변수) | 설명|
|---|---|---|
|Future<?> | Runnable(Runnable task) | Runnalbe 또는 Callable을 작업 큐에 저장, 리턴된 Future를 통해 작업 처리결과를 얻음 |
|Future<V> | submit(Runnable task, V result) | Runnalbe 또는 Callable을 작업 큐에 저장, 리턴된 Future를 통해 작업 처리결과를 얻음 |
|Future<V> | submit(Callable<V> task) | Runnalbe 또는 Callable을 작업 큐에 저장, 리턴된 Future를 통해 작업 처리결과를 얻음 |
   
`Future 객체`는 작업 결과가 아니라 작업이 완료될 때까지 기다렸다가 (지연했다가=블로킹되었다가) 최종 결과를 얻는데 사용된다. 그래서 Future를 **지연 완료(pending completion) 객체**라고 한다. Future의 `get()` 메소드를 호출하면 **스레드가 작업을 완료할 때까지 블로킹**되었다가 작업을 완료하면 **처리 결과를 리턴**한다. 이것이 블로킹을 사용하는 작업 완료 통보 방식이다. 
   
다음은 Future가 가지고 있는 `get()` 메소드를 설명한 표다.   
   
|리턴타입|	메소드(매개변수)|	설명|
|V|	get()|	작업이 완료될 때까지 블로킹 되었다가 처리 결과 V를 리턴|
|V|	get(long timeout, TimeUnit unit)|	timeout 시간 전에 작업이 완료되면 결과 V를 리턴하지만, 작업이 완료되지 않으면 TimeoutException을 발생시킴|
   
   
 리턴 타입인 V는 submit(Runnable task, V result)의 두 번째 매개값인 V 타입이거나 submit(Callable task)의 Callable 타입 파라미터 V 타입이다.   
   
 다음은 세 가지 `submit()` 메소드별로 Future의 `get()` 메소드가 리턴하는 값이 무엇인지 보여준다.

|메소드	|작업 처리 완료 후 리턴타입|	작업 처리 도중 예외 발생|
|submit(Runnable task)	|future.get() -> null	|future.get() -> 예외 발생|
|submit(Runnable task, Integer result)	|future.get() -> int 타입 값	|future.get() -> 예외 발생|
|submit(Callable task)	|future.get() -> String 타입 값	|future.get() -> 예외 발생|

    
Future를 이용한 블로킹 방식의 작업 완료 통보에서 주의할 점은 작업을 처리하는 **스레드가 작업을 완료하기 전까지 `get()` 메소드가 블로킹**되므로 다른 코드를 실행할 수 없다.

 중략...



   
<br/><br/>
   
#### 리턴값이 없는 작업 완료 통보(634p)  
   
 리턴값이 없는 작업일 경우는 Runnable 객체로 생성하면 된다. 다음 코드는 Runnable 객체를 생성하는 방법이다.   
   
```java
Runnable task = new Runnable(){
    @Override
    public void run(){
        // 스레드가 처리할 작업 내용
    }
};
```

 결과값이 없는 작업 처리 요청은 `submit(Runnable task)` 메소드를 이용하면 된다. 결과값이 없음에도 불구하고 다음과 같이 Future 객체를 리턴하는데, 이것은 스레드가 작업 처리를 정상적으로 완료했는지, 아니면 어떤 작업 처리 도중에 예외가 발생했는지 확인하기 위해서이다.   
   
```java
Future future = executorService.submit(task);
```
	
 작업 처리가 정상적으로 완료되었다면 `Future.get()` 메소드는 **null을 리턴**하지만 스레드가 작업 처리 도중 interrupt되면 **InterruptException을 발생**시키고, 작업 처리 도중 예외가 발생하면 **ExecutionException을 발생**시킨다. 그래서 예외 처리를 한다.

	
```java
try {
    future.get();
} catch(InterruptedException e){
    // 작업 처리 도중 스레드가 interrupt 될 경우 실행할 코드
} catch(ExecutionException e){
    // 작업 처리 도중 예외가 발생된 경우 실행할 코드
}
```
	
 -> 한마디로 Future에 스레드가 아무 일 없으면 `null`가지고 코드가 진행되고 예외 생기면 throw 예외를 해서 try-catch를 통해 잡을 수 있다. 다음 예제를 통해 Future를 활용하는 방식을 보자.   
<br/><br/>
**1부터 10까지의 합을 스레드가 처리 예제**   
   
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NoResultExample {
    public static void main(String[] args) {

        System.out.println("이 컴퓨터는 "+Runtime.getRuntime().availableProcessors()+"개의 프로세서를 가지고 있어요.");
        System.out.println("------------------------------------");
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        
        // Runtime.getRuntime().availableProcessors(): 코어 갯수
        // 코어 갯수만큼 스레드 풀에 스레드 생성 가능하게 만듬.

        System.out.println("[작업 처리 요청]");

        Runnable runnable = new Runnable() {			// 한개 스레드 생성함.
            @Override
            public void run() {
                int sum = 0;
                for (int i = 1; i <= 10; i++) {
                    sum += i;
                }
                System.out.println("스레드 이름 : " + Thread.currentThread().getName());
                System.out.println("[처리 결과]: " + sum);
            }
        };

        Future future = executorService.submit(runnable);		// submit 문제가 생겨도 계속 반복함.

        try {
            future.get();					// null 혹은 exception을 받기 때문에 try catch에서 걸러짐.
            System.out.println("[작업 처리 완료]");
        } catch (Exception e) {
            System.out.println("[실행 예외 발생함]" + e.getMessage());
        }

        executorService.shutdown();
    }
}
```
	
**결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/123364463-65de6980-d5af-11eb-8b5d-7c4fcf84452c.png)
   
<br/><br/>

**예외 발생 시키기**
   
```java
        Runnable runnable = new Runnable() {			// 한개 스레드 생성함.
            @Override
            public void run() {
                int sum = 0;
                
                System.out.println(100/sum); 	//예외 발생 코드
                
                for (int i = 1; i <= 10; i++) {
                    sum += i;
                }
                System.out.println("스레드 이름 : " + Thread.currentThread().getName());
                System.out.println("[처리 결과]: " + sum);
            }
        };
```
	
**결과 화면**
	
![image](https://user-images.githubusercontent.com/84966961/123364756-f9b03580-d5af-11eb-9232-17e40cf03a2f.png)
	
  
 
	
<br/><br/>
   
#### 리턴값이 있는 작업 완료 통보(636p)   
   
 스레드풀의 스레드가 작업을 완료한 후에 애플리케이션이 처리 결과를 얻어야 된다면 작업 객체를 Callable로 생성하면 된다.다음은 Callable 객체를 생성하는 코드인데, 주의할 점은 제네릭 타입 파라미터 T는 `call()` 메소드가 리턴하는 타입이 되도록 한다.   
	
```java
Callable<T> task = new Callable<T>(){	// 제네틱 타입으로 되어있다.
    @Override
    public T call() throws Exception(){
        // 스레드가 처리할 작업 내용
        return T;
    }
};
```
	
 Callable 작업의 처리 요청은 Runnable 작업과 마찬가지로 ExecutorService의 `submit()` 메소드를 호출하면 된다. `submit()` 메소드는 작업 큐에 Callable 객체를 저장하고 **즉시 Future를 리턴**한다. 이때 T는 `call()` 메소드가 리턴하는 타입이다.
	
	```java
	Future<T> future = execitprService.submit(task);
	```
 
 스레드풀의 스레드가 Callable 객체의 `call()` 메소드를 모두 실행하고 T 타입의 값을 리턴하면, Future의 `get()` 메소드는 블로킹이 해제되고 T타입의 값을 리턴한다.
	
```java
try {

} catch(InterruptedExcpetion e){
    // 작업 처리 도중 스레드가 interrup 될 경우 실행할 코드
} catch (ExecutionException e){
    // 작업 처리 도중 예외가 발생된 경우 실행할 코드
}
```
<br/>
	
**1부터 10까지 합 리턴을 Callable 객체로 스레드 처리**
	
```java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ResultByCallableExample {
    public static void main(String[] args) {
    	// 프로세서 갯수만큼 스레드풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        System.out.println("[작업 처리 요청]");
        Callable<Integer> task = new Callable<Integer>() {	// 제네틱 타입으로 처리.
            @Override
            public Integer call() throws Exception {	// call()해서 리턴값 Integer로 받음
                int sum = 0;
                for (int i = 1; i <= 10; i++) {
                    sum += i;
                }

                return sum;								// 리턴 sum
            }
        };
        Future<Integer> future = executorService.submit(task);

        try {
           int sum = future.get();						// sum이 일로옴.
            System.out.println("[처리 결과] " + sum);
            System.out.println("[작업 처리 완료] ");


        } catch (Exception e) {
            System.out.println("[실행 예외 발생함]" + e.getMessage());
        }

        executorService.shutdown();
    }
}
```
	
**결과 화면**   
	
![image](https://user-images.githubusercontent.com/84966961/123365389-26b11800-d5b1-11eb-81f6-75543cebf4c7.png)

	
**0625 자바 수업 종료...**
	
<br/><br/>
	
#### 작업 처리 결과를 외부 객체에 저장
	
 상황에 따라서 스레드가 작업한 결과를 외부 객체에 저장해야 할 경우도 있다. 예를 들어 스레드가 작업 처리를 완료하고 외부 Result 객체에 작업 결과를 저장하면, 애플리케이션이 Result 객체를 사용해서 어떤 작업을 진행할 수 있을 것이다. 대개 Result 객체는 공유 객체가 되어, 두 개 이상의 스레드 작업을 취합할 목적으로 이용된다.
	
![image](https://user-images.githubusercontent.com/84966961/123365672-a9d26e00-d5b1-11eb-817a-ddc19ce1344f.png)
	
 이런 작업을 하기 위해서 ExecutorService의 `submit(Runnable task, V result)` 메소드를 사용할 수 있는데, V가 바로 Result 타입이 된다. 메소드를 호출하면 스레드가 작업을 완료할 때까지 블로킹 되었다가 작업을 완료하면 V 타입 객체를 리턴한다. 리턴된 객체는 `submit()`의 두 번째 매개값으로 준 객체와 동일한데, 차이점은 스레드의 처리 결과가 내부에 저장되어 있다는 것이다.
	
```java
Result result = '...'
Runnable task = new Task(result);
Future<Result> future = executorService.submit(task, result);
result = future.get();
```
	
 작업 객체는 Runnable 구현 클래스로 생성하는데, 주의할 점은 스레드에서 결과를 저장하기 위해 외부 Result 객체를 사용해야 하므로 생성자를 통해 Result 객체를 주입받도록 해야 한다.
	
```java
class Task implements Runnable{

    private Result result;

    public Task(Result result){
        this.result = result;
    }
    @Override
    public void run(){
        // 작업 코드
        // 처리 결과를 result 저장
    }
}
```
	
	
	
**1부터 10까지 합 계산 2개의 스레드 풀 예제**
	
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ResultByRunnableExample {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        System.out.println("[작업 처리 요청]");
        class Task implements  Runnable{		// 작업정의

            private Result result;

            public Task(Result result){
                this.result = result;
            }

            @Override
            public void run() {
                int sum = 0;
                for (int i = 1; i <= 10; i++) {
                    sum += i;
                }
                result.addValue(sum); // Result 객체에 작업 결과 저장
            }
        }

	// 두가지 작업 처리를 요청
        Result result = new Result();
        Runnable task1 = new Task(result);
        Runnable task2 = new Task(result);

        Future<Result> future1 = executorService.submit(task1, result);
        Future<Result> future2 = executorService.submit(task2, result);

        try {
	// 두 가지 작업 결과를 취합
            result = future1.get();
            result = future2.get();
            System.out.println("[처리 결과] " + result.accumValue);

        } catch (Exception e){
            e.printStackTrace();
            System.out.println("[실행 예외 발생함] " + e.getMessage());
        }

        executorService.shutdown();
    }
}

// 처리 결과를 저장하는 Result 클래스
class Result{
    int accumValue;
    synchronized void addValue(int value){
        accumValue += value;
    }
}
```
	
	


<br/><br/>
<hr/>

# 교재 653p : 13 제네릭
## 교재 654p : 13.1 왜 제네릭을 사용해야 하는가?
	
 타입을 정의하지 아니하고 변수를 선언할때 사용한다.
	
```java
List list = new ArrayList();
list.add("hello");
String str = (String) list.get(0);	// 타입 변환을 해야한다.
```
	
 ArrayList를 배열처럼 사용할 수 있다. 하지만 모든 클래스의 부모인 object 타입으로 배열을 사용하게 된다. 이럴 때 모든 클래스 타입을 받을 수 는 있지만(기본형은 못사용한다. 오직 객체만 사용 가능하다.) 문제점은 사용할 때 모든 타입을 알고 있어야 캐스팅 형변환(Casting)을 해서 결국 사용할 수 있다.   
	
 이런 불편함을 없애기 위해서 제네릭이 생겨났다.
	
 다음과 같이 제네릭 코드로 수정하면 List에 저장되는 요소를 String 타입으로 국한하기 때문에 요소를 찾아올 때 타입 변환을 할 필요가 없어 프로그램 성능이 향상된다.
	
```java
List<String> list = new ArrayList<String>();
list.add("hello");
String str = list.get(0); // 타입 변환을 하지 않습니다.
```
	
	


<br/><br/>
<hr/>
   




이미지 출처 : https://velog.io/@sa1341/%EB%A9%80%ED%8B%B0-%EC%8A%A4%EB%A0%88%EB%93%9C%EC%9D%98-%EC%83%81%ED%83%9C-%EC%A0%9C%EC%96%B4
