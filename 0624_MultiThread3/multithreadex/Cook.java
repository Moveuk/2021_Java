package multithreadex;

public class Cook implements Runnable {

	private Table table;
	
	// ������ ���鶧 ������ ���� �������� ������ ��.
	public Cook(Table table) {			// table�� �ʱ�ȭ �ϰԲ� ������ ����.
		super();
		this.table = table;
	}


	@Override
	public void run() {
		while (true) {
			int idx = (int)(Math.random()* table.dishNum());	// 0~2�� �迭���̴ϱ� +1����.

			table.add(table.dishNames[idx]);					// ������ ����.
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
			
		}
	}

}
