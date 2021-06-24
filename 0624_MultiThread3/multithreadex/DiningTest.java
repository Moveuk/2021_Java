package multithreadex;

public class DiningTest {

	public static void main(String[] args) throws Exception {
		Table table = new Table();
		
		Cook cook = new Cook(table);
		Customer customer = new Customer(table, "donut");			// 도넛 먹는 사람.
		Customer customer2 = new Customer(table, "burger");			// 버거 먹는 사람.
		
		
		Thread cooker = new Thread(cook,"Cook");				
		Thread customer_donut = new Thread(customer,"Customer");
		Thread customer_burger = new Thread(customer2,"Customer2");

		cooker.start();
		customer_donut.start();
		customer_burger.start();
		
		Thread.sleep(2000);											// main 스레드 o.1초 sleep
		System.exit(0);												// 강제로 프로그램 종료 하고 싶을 때 사용
		
	}

}
