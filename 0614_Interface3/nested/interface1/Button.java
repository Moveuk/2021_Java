package nested.interface1;

public class Button {
	
	OnClickListener listener;
	
	void setOnClickListener(OnClickListener listener) {	// �Ű� ������ Call �� Message�� ��� �� �� ����.
		this.listener = listener;
	}
	
	void touch() {
		listener.onClick();
	}
	
	interface OnClickListener {		// ���ο��� listener�� �پ��� Ÿ��(�������� ���ؼ�, �پ��� �ڽ� ��ü�� �ֱ� ���ؼ�)�� �ֱ� ���� ���Ǹ� ��.
		void onClick();
	}
}
