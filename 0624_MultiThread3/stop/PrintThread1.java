package stop;

public class PrintThread1 extends Thread {
	private boolean stop;
	
	public void setStop(boolean stop) {
	  this.stop = stop;				// false�� �ʱ�ȭ
	}
	
	public void run() {	
		while(!stop) {
			System.out.println("���� ��");
		}	
		System.out.println("�ڿ� ����");
		System.out.println("���� ����");
	}
}

