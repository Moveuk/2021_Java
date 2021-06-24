package daemon;

public class DaemonExample {
	public static void main(String[] args) {
		AutoSaveThread autoSaveThread = new AutoSaveThread();
		autoSaveThread.setDaemon(true);				// 데몬 스레드 생성
		autoSaveThread.start();						//
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		
		System.out.println("메인 스레드 종료");
	}												// 이틈에도 데몬 스레드가 살아있고 실행될 수 있다.
}
