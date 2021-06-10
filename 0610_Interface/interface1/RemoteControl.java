package interface1;

public interface RemoteControl {
	public static final int MAX_VOLUME = 10; //public static final ��������(�ǹ̴� ����)
	int MIN_VOLUME = 0;
	
	public abstract void turnOn();
	void turnOff();
	void setVolume(int volume);
	
	// 348p : 8.2.4 ����Ʈ �޼ҵ� ����
	default void setMute(boolean mute) { // public �� �ڵ����� ������ �������� ����.
		if(mute) {
			System.out.println("����ó��");
		} else {
			System.out.println("��������");
		}
	}
	
	// 349p : 8.2.5 ���� �޼ҵ� ����
	static void ChangeBattery() {  // public �� �ڵ����� ������ �������� ����.
		System.out.println("������ ��ü");
	}
}
