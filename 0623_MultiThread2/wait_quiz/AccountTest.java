package wait_quiz;

public class AccountTest {

	public static void main(String[] args) {

		RunnableEx r = new RunnableEx();
		Thread p1 = new Thread(r);
		Thread p2 = new Thread(r);
		String name2 = p2.getName();
		
		p1.start();
		p2.start();

	}

}
