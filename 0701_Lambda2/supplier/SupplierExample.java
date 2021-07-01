package supplier;

import java.util.function.IntSupplier;

public class SupplierExample {
	public static void main(String[] args) {
		IntSupplier intSupplier = () -> {
			int num = (int) (Math.random() * 6) + 1;
			return num;
		};
		
		int num = intSupplier.getAsInt();			// 1 ~ 6 ·£´ı ¸®ÅÏ
		System.out.println("´«ÀÇ ¼ö: " + num);
	}
}
