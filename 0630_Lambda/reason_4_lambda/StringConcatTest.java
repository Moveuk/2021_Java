package reason_4_lambda;

public class StringConcatTest {

	public static void main(String[] args) {

		StringConcatImpl sc = new StringConcatImpl();
		String s1 = "java ";
		String s2 = "Version 10";
		
		System.out.println("Ŭ������ �̿��� ��� : �������̽� + ���� Ŭ���� + �׽�Ʈ Ŭ����");
		sc.makeString(s1, s2);
				
		System.out.println("���ٽĸ� �̿��� ��� : �������̽� + ���ٽ� �׽�Ʈ Ŭ����");
		StringConcat scfi = (a, b) -> {
			System.out.println(a + b);
		};
		
		scfi.makeString(s1, s2);
		
		System.out.println("");
		System.out.println("���ٽ��� Ȱ���ϰ� �Ǹ� �߰��� ���� Ŭ������ �ʿ� ������ ȿ���� �����Ѵ�.");
	}
}
