package reentrantlock;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Table {

	String[] dishNames = { "donut", "donut", "burger" };	// ���̺� �ö� ���� ����.
	final int MAX_FOOD = 6;		// �ִ� ���� ����.
	public ArrayList<String> dishes = new ArrayList<>();	// ���ÿ� ������ ��´�.
	
	private ReentrantLock lock = new ReentrantLock(); // ���۾� ����ȭ�� ���Ͽ� ReentrantLock�� ���� ����ȭ�� ��.
	private Condition forCook = lock.newCondition();	// �丮��� ������ ���� ����ȭ ������ �ɱ����� ���� ��ü ����.
	private Condition forCust = lock.newCondition();
	
	//���� �����带 ����ȭ �ϱ����� ����ϴ� 3���� �ֿ� �޼ҵ� : wait, notify, notifyAll��
	//ReentrantLock������ await(), signal(), signalAll() �޼ҵ�� ����. 
	
	// forCook, forCust�� ������ Waiting Pool�� ����� �����̴�. �̷��� �Ǹ� �����ؼ� notify�� �� �ִ�.


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
			System.out.println("Cook�� " + dish+ "�� ��������ϴ�.");
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
						System.out.println(name+"�� "+dishes.get(i)+"�� �Ծ����ϴ�.");
						dishes.remove(i);	// ���� ���� �ñ׳�.
						System.out.println("Dishes" + dishes.toString());
						forCook.signal();	// ���� �Ծ �ڸ��� ������� �丮�翡�� ���� ������ �ñ׳� ����.
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
	
	public int dishNum() {				// ���� ���ڸ� �ֱ� ���� ���� ���� ���� �� ���� ���.
		return dishNames.length;
	}
}
