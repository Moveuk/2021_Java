package trycatchfinally;

public class NumberFormatExceptionExample {
	public static void main(String[] args) {
		String data1 = "100";
		String data2 = "a100";
		String string = null;
				
		int value1 = Integer.parseInt(data1);
		int value2 = 0;
		try {
//			System.out.println(string.toString());	// NullPointException �� catch�� ������ ������ Exception�� �����.
//			System.out.println(value1 / 0);		// catch�� ���ǿ��� ���� ó�� Ÿ���� �ٸ��� ó������ ���Ѵ�. �׷��� �ٸ� catch���� �߰��Ѵ�.(����catch��)
			value2 = Integer.parseInt(data2);
		} catch (ArithmeticException | NumberFormatException e) {		// ��Ƽ catch��	
			e.printStackTrace();  				// ���ܰ� � ������ ���� �߻��ߴ���.
			System.out.println(e.getMessage() + " : ������ 0���� �����ø� �ȵ˴ϴ�.");
		} catch (Exception e) {					// ���� ���� ���� Ŭ�����̹Ƿ� ��� ���ܸ� �޾��ش�. 
			System.out.println(e.getMessage() + " : ���ܰ� �߻��߽��ϴ�.");
		} finally {				// ���ܿʹ� ������ ������ �ѹ��� �����ؾ� �� �� �ۼ�
			System.out.println("���� ó���� ���� ������ �Ϸ��մϴ�.");
		}
		
		int result = value1 + value2;
		System.out.println(data1 + "+" + data2 + "=" + result);
	}
}

