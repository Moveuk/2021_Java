package runtimeException;

public class ClassCastExceptionExample {
	public static void main(String[] args) {
		Dog dog = new Dog();
		changeDog(dog);
		
		Cat cat = new Cat();
		changeDog(cat);					// Dog dog = (Dog) cat; 이기 때문에 Dog과 Cat은 아무런 관계가 없으므로 캐스팅 불가.
	}
	
	public static void changeDog(Animal animal) {
		//if(animal instanceof Dog) {
			Dog dog = (Dog) animal; 				//ClassCastException 발생 가능
		//} 
	}
}

class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}

