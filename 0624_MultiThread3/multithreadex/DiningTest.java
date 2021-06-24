package multithreadex;

public class DiningTest {

	public static void main(String[] args) throws Exception {
		Table table = new Table();
		
		Cook cook = new Cook(table);
		Customer customer = new Customer(table, "donut");			// ���� �Դ� ���.
		Customer customer2 = new Customer(table, "burger");			// ���� �Դ� ���.
		
		
		Thread cooker = new Thread(cook,"Cook");				
		Thread customer_donut = new Thread(customer,"Customer");
		Thread customer_burger = new Thread(customer2,"Customer2");

		cooker.start();
		customer_donut.start();
		customer_burger.start();
		
		Thread.sleep(2000);											// main ������ o.1�� sleep
		System.exit(0);												// ������ ���α׷� ���� �ϰ� ���� �� ���
		
	}

}
