package thread;

import java.awt.Toolkit;

// Thread ��� Ŭ������ �̿��� thread ������ ��ü.
public class BeepPrintExample_Thread_Alter {
	public static void main(String[] args) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				for (int i = 0; i < 5; i++) {
					toolkit.beep();
					try {
						Thread.sleep(500);
					} catch (Exception e) {	}
				}
			}
		};
		thread.start();
		
		for(int i=0; i<5; i++) {
			System.out.println("��");
			try { Thread.sleep(500); } catch(Exception e) {}
		}
	}
}
