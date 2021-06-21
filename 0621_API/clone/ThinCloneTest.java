package clone;

public class ThinCloneTest {

	public static void main(String[] args) {

		ThinClone original = new ThinClone("blue", "ȫ�浿", "1234", 20, true);

		ThinClone cloned = original.getMember();
		
		cloned.password = "qqqqqq";
		
		System.out.println(original.password);
		System.out.println(cloned.password);
		
	}

}
