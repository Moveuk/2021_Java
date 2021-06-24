package multithreadex;

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
				Thread.sleep(100);
				
			} catch (InterruptedException e) {
			}
			
			String name = Thread.currentThread().getName();
			
			table.remove(food);
			System.out.println(name+"이 "+ food + "을 잘 먹었다.");
		}
	}
}
