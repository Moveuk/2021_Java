package interface1;

public class RemoteControlTest {

	public static void main(String[] args) {
		RemoteControl t = new Television();
		RemoteControl a = new Television();
		
		t.turnOn(); // TV 켜다.
		a.turnOn(); // 오디오 끄다.
		t.turnOff();
		a.turnOff();
		
		RemoteControl rc = new RemoteControl() { //익명 객체를 사용하여 인터페이스를 생성하였음.
			@Override
			public void turnOn() {
				System.out.println("익명객체 켜기");
			}
			@Override
			public void turnOff() {
			}
			@Override
			public void setVolume(int volume) {
			}
		};
		
		rc.turnOn(); // 익명객체 실행문
		
		Searchable sa = new SmartTelevision();
		sa.search("동물");
		
		a.setMute(true);
		
		RemoteControl.ChangeBattery();
//		a.ChangeBattery(); // 사용 불가능.
	}
}
