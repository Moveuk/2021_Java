package join;

public class JoinExample {
	public static void main(String[] args) {
		SumThread sumThread = new SumThread();
		sumThread.start();
		try {							// 조인을 주석처리 하면 합이 0으로나옴
			sumThread.join();			// SumThread가 끝날 때까지 기다려줌.
		} catch (InterruptedException e) {
		}
		System.out.println("1~100 합: " + sumThread.getSum());
	}
}

