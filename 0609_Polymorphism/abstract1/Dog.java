package abstract1;

public class Dog extends Animal {

	public Dog(String kind) {  // �θ𿡼� ������ �ʱ�ȭ�� �������Ƿ� �ڽ�Ŭ�������� �ʱ�ȭ ����.
		this.kind = kind;
	}

	@Override
	public void sound() {
		System.out.println("�۸�");
	}

}
