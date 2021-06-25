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
