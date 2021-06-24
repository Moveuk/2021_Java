package stop;

public class asd {
	static boolean stop;

	public static void main(String[] args) {
		int i=0;
		
		while (!stop) {
			System.out.println(i++);
		}
	}

}
