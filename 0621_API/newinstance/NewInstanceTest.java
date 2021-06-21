package newinstance;

public class NewInstanceTest {

	public static void main(String[] args) {

		try {
			Class clazz = Class.forName("newinstance.SendAction"); // 해당 객체의 존재 유무만 판단.
			SendAction action = (SendAction) clazz.newInstance(); // newInstance 예외 경우
			action.exectue();
			Class clazz2 = Class.forName("newinstance.ReceiveAction"); // 해당 객체의 존재 유무만 판단.
			ReceiveAction action2 = (ReceiveAction) clazz2.newInstance(); // newInstance 예외 경우
			action2.exectue();
		} catch (ClassNotFoundException e) { // forName 예외 경우
			// forName 예외 경우 : 해당 클래스를 찾지 못할 경우
			e.printStackTrace();
		} catch (InstantiationException e) {
			// newInstance 예외 경우1 : 해당 클래스가 추상 클래스이거나 인터페이스일 경우
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// newInstance 예외 경우2 : 클래스나 생성자가 접근 제한자로 인해 접근할 수 없을 경우
			e.printStackTrace();
		}
	}
}
