package nested.interface1;

public class ButtonExample {

	public static void main(String[] args) {
		Button btn = new Button();
		
		btn.setOnClickListener(new CallListener());		// listener�� ���ϴ� ���� Ŭ������ �ִ� ���.
		btn.touch();									// �ش� �̺�Ʈ �����ʿ� �ִ� onClick �޼ҵ� ���.
		
		btn.setOnClickListener(new MessageListener());
		btn.touch();

	}

}
