package thread;

import java.awt.Toolkit;

public class BeepPrintExample_lambda {

	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			for (int i = 0; i < 5; i++) {
				toolkit.beep();
				try {Thread.sleep(500);} 
				catch (Exception e) {}
			}
		});
		thread.start();

		for (int i = 0; i < 5; i++) {
			System.out.println("¶ò");
			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
		}
	}
}
