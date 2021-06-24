package multithreadex;

import java.util.ArrayList;

public class Table {
	String[] dishNames = { "donut", "donut", "burger" }; 	// ���̺� �ö� �� �ִ� ������ �迭�� ������.

	final int MAX_FOOD = 6; 								// ���̺� �ִ�� �ø� �� �ִ� ���� ������ ����.
			
	public ArrayList<String> dishes = new ArrayList<>(); 	// ���̺� ������ �� �ִ� ����Ҹ� �迭����Ʈ�� ����.

	public synchronized void add(String dish) { 			// ���̺� ������ �����ϴ� ����� �޼ҵ�

		while (dishes.size() >= MAX_FOOD) { 				
			String name = Thread.currentThread().getName();
			System.out.println(name + " is waiting");
			
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		dishes.add(dish); 									// �ܼ� �迭�� �ƴ϶� ��ü ���·� ����ϱ� ������
															// index �ѹ��� �����ϴ� ���� �ƴ϶� add() �޼ҵ带 �̿��Ͽ� ���ø� �߰���.
		notify();											// ������ �ҷ�����.
		System.out.println(Thread.currentThread().getName()+"�� "+ dish +" �� ���� ��������ϴ�.");
		System.out.println("Dishes : " + dishes.toString());
	}

	public void remove(String dishName) { 					// �Դ´ٴ� ���� �迭���� ����ٴ� ��.
		synchronized (this) {								// ����ȭ ����� ��Ű�� Ŭ���� ������ ��ΰ� ����ȭ ������.
			String name = Thread.currentThread().getName();
			while (dishes.size() == 0) {					// ���̺� ���ľ����� ����.
				System.out.println(name + " is waiting");
				try {
					wait();						
				} catch (Exception e) {}
			}

			while (true) {
				for (int i = 0; i < dishes.size(); i++) {	// ���̺� ���� ���� ������ŭ �ݺ��Ͽ� Ȯ����.
					if (dishName.equals(dishes.get(i))) { 	// �Ű� ������ i��° dishes ���̶� ������
						dishes.remove(i); 					// ���� : �Һ��ڰ� ����.
						notify();
						return;
					} 			
				}
				try {
					System.out.println(name + " is waiting");
					wait();						
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int dishNum() { 									// ���� �� �ִ� ������ ����.
		return dishNames.length;
	}
}
