package throws1;

public class ThrowException2 {

	public static void main(String[] args) /*throws Exception*/ {
		// �� �ٽ� ���ܸ� ���ѱ� �� ������ �̴� �ٶ����� ���� ó�� ����� �ƴ�.
		
		try {
			method1(); 				// method1 ȣ���Ϸ��� ���� ó���ؾ���(try-catch). �ٵ� �� ���ܸ� �ѱ�.		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void method1() throws Exception{ // �� ���� ����. �� ������ �ѱ�. 
		method2();
		throw new ArithmeticException("������ ���ܰ� �߻�!");	// �տ��� ���� �߻��ؼ� ���ǵ���.
	}

	public static void method2() throws Exception {	// ���ܸ� ��������. ���� ����� ó���ϰ� �����.
		throw new Exception("���ܰ� �߻�!");
	}
}
