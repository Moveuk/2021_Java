package throws1;

public class ThrowException {

	public static void main(String[] args) {
		
		try {	// ���� ���� �߻�
			throw new Exception("���Ƿ� ���ܹ߻�");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage()); // ���Ƿ� ���� �߻��� ������ ��.
		} finally {
			
		}
	}
}
