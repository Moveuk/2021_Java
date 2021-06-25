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
