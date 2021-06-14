package nested.interface1;

public class ButtonExample {

	public static void main(String[] args) {
		Button btn = new Button();
		
		btn.setOnClickListener(new CallListener());		// listener에 원하는 구현 클래스를 넣는 기능.
		btn.touch();									// 해당 이벤트 리스너에 있는 onClick 메소드 사용.
		
		btn.setOnClickListener(new MessageListener());
		btn.touch();

	}

}
