package abstract1;

public class AnimalTest {
	public static void main(String[] args) {
		Dog dog = new Dog("������");
		Cat cat = new Cat("�����");
		dog.sound();
		cat.sound();
		System.out.println("-----");
		
		//������ �ڵ� Ÿ�� ��ȯ
		Animal animal = null;
		animal = new Dog("������");		// �θ� Ÿ������ �Ű� ������ ���
		animal.sound();					// animal�� ������ ���ǵ��� ���� �޼ҵ带 �ִ��� ���빰�� �߳���.
		animal = new Cat("�����");
		animal.sound();
		System.out.println("-----");
		
		//�Ű������� �ڵ� Ÿ�� ��ȯ
		animalSound(new Dog("������")); // Animal Ÿ������ �� �Ű� ������ �ٲ�.
		animalSound(new Cat("�����"));
	}
	
	public static void animalSound(Animal animal) {
		animal.sound();
	}
}
