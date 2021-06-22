package thread;
// Thread 상속 클래스를 이용한 thread 실행.
public class BeepPrintExample_Thread {
	public static void main(String[] args) {
		Thread thread = new BeepThread();
		thread.start();
		
		for(int i=0; i<5; i++) {
			System.out.println("띵");
			try { Thread.sleep(500); } catch(Exception e) {}
		}
	}
}
