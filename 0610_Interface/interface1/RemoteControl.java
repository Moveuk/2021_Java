package interface1;

public interface RemoteControl {
	public static final int MAX_VOLUME = 10; //public static final 생략가능(의미는 존재)
	int MIN_VOLUME = 0;
	
	public abstract void turnOn();
	void turnOff();
	void setVolume(int volume);
	
	// 348p : 8.2.4 디폴트 메소드 선언
	default void setMute(boolean mute) { // public 이 자동으로 컴파일 과정에서 붙음.
		if(mute) {
			System.out.println("무음처리");
		} else {
			System.out.println("무음해제");
		}
	}
	
	// 349p : 8.2.5 정적 메소드 선언
	static void ChangeBattery() {  // public 이 자동으로 컴파일 과정에서 붙음.
		System.out.println("건전지 교체");
	}
}
