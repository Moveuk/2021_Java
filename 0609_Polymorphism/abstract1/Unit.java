package abstract1;

public abstract class Unit {
	int x, y;
	abstract void move(int x, int y); // public이 생략되었지만 public이 아닌 것이 아니다.
	void stop() {};
}
