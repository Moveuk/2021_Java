package runtimeException;

public class ClassCastExceptionExample {
	public static void main(String[] args) {
		Dog dog = new Dog();
		changeDog(dog);
		
		Cat cat = new Cat();
		changeDog(cat);					// Dog dog = (Dog) cat; �̱� ������ Dog�� Cat�� �ƹ��� ���谡 �����Ƿ� ĳ���� �Ұ�.
	}
	
	public static void changeDog(Animal animal) {
		//if(animal instanceof Dog) {
			Dog dog = (Dog) animal; 				//ClassCastException �߻� ����
		//} 
	}
}

class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}

