package wrapper;

public class WrapperExample {

	public static void main(String[] args) {
		// wrapper
		// �⺻���� ���������� ����ȯ�Ѵ�.(����ڽ�)
		
//		Integer i = new Integer(100);	// 100�� ������ �ִ� ���� �ƴ϶� �������� �ּҰ��� ������ �ִ� ���̴�.
//		Double d = new Double(20.3);
//		
//		System.out.println(i.toString());
//		System.out.println(d);
		
		int x = 10;
		Integer i = x;
		
		System.out.println(i);
		
		Integer i2 = 30; 		// �ٷ� ������ ���� �� �ִ�.
		int y = i2;				// ������ ������ �⺻ Ÿ�Կ� �־ �ȴ�.
		
		Object obj = y;			// new Integer(y); ��ü�� �����Ǽ� ��.
		
		System.out.println(i + i2);	// int + Integer ��� ����
		
		int pi =Integer.parseInt("100");
		double di = Double.parseDouble("23.1");
		

	}

}
