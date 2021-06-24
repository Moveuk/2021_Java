package multithreadex;

public class Cook implements Runnable {

	private Table table;
	
	// 스레드 만들때 다음과 같은 형식으로 만들어야 함.
	public Cook(Table table) {			// table로 초기화 하게끔 생성자 생성.
		super();
		this.table = table;
	}


	@Override
	public void run() {
		while (true) {
			int idx = (int)(Math.random()* table.dishNum());	// 0~2의 배열값이니까 +1안함.

			table.add(table.dishNames[idx]);					// 음식을 만듬.
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
			
		}
	}

}
