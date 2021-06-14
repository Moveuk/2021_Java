package nested.interface1;

public class Button {
	
	OnClickListener listener;
	
	void setOnClickListener(OnClickListener listener) {	// 매개 변수에 Call 과 Message가 모두 들어갈 수 있음.
		this.listener = listener;
	}
	
	void touch() {
		listener.onClick();
	}
	
	interface OnClickListener {		// 내부에서 listener에 다양한 타입(다형성을 위해서, 다양한 자식 객체를 넣기 위해서)을 넣기 위해 정의를 함.
		void onClick();
	}
}
