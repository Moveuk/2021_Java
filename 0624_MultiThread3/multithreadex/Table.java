package multithreadex;

import java.util.ArrayList;

public class Table {
	String[] dishNames = { "donut", "donut", "burger" }; 	// 테이블에 올라갈 수 있는 음식을 배열로 정의함.

	final int MAX_FOOD = 6; 								// 테이블에 최대로 올릴 수 있는 음식 갯수를 정함.
			
	public ArrayList<String> dishes = new ArrayList<>(); 	// 테이블에 저장할 수 있는 저장소를 배열리스트로 만듬.

	public synchronized void add(String dish) { 			// 테이블에 음식을 저장하는 기능의 메소드

		while (dishes.size() >= MAX_FOOD) { 				
			String name = Thread.currentThread().getName();
			System.out.println(name + " is waiting");
			
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		dishes.add(dish); 									// 단순 배열이 아니라 객체 형태로 사용하기 때문에
															// index 넘버로 구분하는 것이 아니라 add() 메소드를 이용하여 접시를 추가함.
		notify();											// 스레드 불러내기.
		System.out.println(Thread.currentThread().getName()+"이 "+ dish +" 한 개를 만들었습니다.");
		System.out.println("Dishes : " + dishes.toString());
	}

	public void remove(String dishName) { 					// 먹는다는 것은 배열에서 지운다는 것.
		synchronized (this) {								// 동기화 블록을 시키면 클래스 내부의 모두가 동기화 연동됨.
			String name = Thread.currentThread().getName();
			while (dishes.size() == 0) {					// 테이블에 음식없으면 정지.
				System.out.println(name + " is waiting");
				try {
					wait();						
				} catch (Exception e) {}
			}

			while (true) {
				for (int i = 0; i < dishes.size(); i++) {	// 테이블 위의 음식 갯수만큼 반복하여 확인함.
					if (dishName.equals(dishes.get(i))) { 	// 매개 변수가 i번째 dishes 값이랑 같으면
						dishes.remove(i); 					// 삭제 : 소비자가 먹음.
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

	public int dishNum() { 									// 만들 수 있는 음식의 갯수.
		return dishNames.length;
	}
}
