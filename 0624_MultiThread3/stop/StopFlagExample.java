package stop;

public class StopFlagExample {
	public static void main(String[] args)  {
		PrintThread1 printThread = new PrintThread1();
		printThread.start();
		
		try {
			Thread.sleep(1000);						// 1�� ���
		} catch (InterruptedException e) {
		}
		
		printThread.setStop(true);					// ����
	}
}

