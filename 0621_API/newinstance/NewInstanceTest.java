package newinstance;

public class NewInstanceTest {

	public static void main(String[] args) {

		try {
			Class clazz = Class.forName("newinstance.SendAction"); // �ش� ��ü�� ���� ������ �Ǵ�.
			SendAction action = (SendAction) clazz.newInstance(); // newInstance ���� ���
			action.exectue();
			Class clazz2 = Class.forName("newinstance.ReceiveAction"); // �ش� ��ü�� ���� ������ �Ǵ�.
			ReceiveAction action2 = (ReceiveAction) clazz2.newInstance(); // newInstance ���� ���
			action2.exectue();
		} catch (ClassNotFoundException e) { // forName ���� ���
			// forName ���� ��� : �ش� Ŭ������ ã�� ���� ���
			e.printStackTrace();
		} catch (InstantiationException e) {
			// newInstance ���� ���1 : �ش� Ŭ������ �߻� Ŭ�����̰ų� �������̽��� ���
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// newInstance ���� ���2 : Ŭ������ �����ڰ� ���� �����ڷ� ���� ������ �� ���� ���
			e.printStackTrace();
		}
	}
}
