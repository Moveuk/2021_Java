package join;

public class JoinExample {
	public static void main(String[] args) {
		SumThread sumThread = new SumThread();
		sumThread.start();
		try {							// ������ �ּ�ó�� �ϸ� ���� 0���γ���
			sumThread.join();			// SumThread�� ���� ������ ��ٷ���.
		} catch (InterruptedException e) {
		}
		System.out.println("1~100 ��: " + sumThread.getSum());
	}
}

