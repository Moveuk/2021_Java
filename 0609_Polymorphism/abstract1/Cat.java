package abstract1;

public class Cat extends Animal {

	public Cat(String kind) {  // �θ𿡼� ������ �ʱ�ȭ�� �������Ƿ� �ڽ�Ŭ�������� �ʱ�ȭ ����.
		this.kind = kind;
	}

	@Override
	public void sound() {
		System.out.println("�߿�");
	}

}
