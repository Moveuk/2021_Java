package stop;

public class StopFlagExample {
	public static void main(String[] args)  {
		PrintThread1 printThread = new PrintThread1();
		printThread.start();
		
		try {
			Thread.sleep(1000);						// 1초 대기
		} catch (InterruptedException e) {
		}
		
		printThread.setStop(true);					// 정지
	}
}

