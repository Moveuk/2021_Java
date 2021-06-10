package abstract1;

public class Dog extends Animal {

	public Dog(String kind) {  // 부모에서 생성자 초기화를 안했으므로 자식클래스에서 초기화 가능.
		this.kind = kind;
	}

	@Override
	public void sound() {
		System.out.println("멍멍");
	}

}
