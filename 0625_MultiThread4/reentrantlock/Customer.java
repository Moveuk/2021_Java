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
