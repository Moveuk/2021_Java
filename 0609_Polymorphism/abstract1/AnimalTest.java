package abstract1;

public class AnimalTest {
	public static void main(String[] args) {
		Dog dog = new Dog("강아지");
		Cat cat = new Cat("고양이");
		dog.sound();
		cat.sound();
		System.out.println("-----");
		
		//변수의 자동 타입 변환
		Animal animal = null;
		animal = new Dog("강아지");		// 부모 타입으로 매개 변수를 잡고
		animal.sound();					// animal에 내용이 정의되지 않은 메소드를 넣더라도 내용물이 잘나옴.
		animal = new Cat("고양이");
		animal.sound();
		System.out.println("-----");
		
		//매개변수의 자동 타입 변환
		animalSound(new Dog("강아지")); // Animal 타입으로 각 매개 변수가 바뀜.
		animalSound(new Cat("고양이"));
	}
	
	public static void animalSound(Animal animal) {
		animal.sound();
	}
}
