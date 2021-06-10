package interface1;

public class RemoteControlTest {

	public static void main(String[] args) {
		RemoteControl t = new Television();
		RemoteControl a = new Television();
		
		t.turnOn(); // TV �Ѵ�.
		a.turnOn(); // ����� ����.
		t.turnOff();
		a.turnOff();
		
		RemoteControl rc = new RemoteControl() { //�͸� ��ü�� ����Ͽ� �������̽��� �����Ͽ���.
			@Override
			public void turnOn() {
				System.out.println("�͸�ü �ѱ�");
			}
			@Override
			public void turnOff() {
			}
			@Override
			public void setVolume(int volume) {
			}
		};
		
		rc.turnOn(); // �͸�ü ���๮
		
		Searchable sa = new SmartTelevision();
		sa.search("����");
		
		a.setMute(true);
		
		RemoteControl.ChangeBattery();
//		a.ChangeBattery(); // ��� �Ұ���.
	}
}
