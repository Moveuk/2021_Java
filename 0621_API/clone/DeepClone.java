package clone;

import java.util.Arrays;

public class DeepClone implements Cloneable{

	public String name;
	public int age;
	public int[] scores;	// 얕은 복제와 달리 배열,
	public Car car;			// 클래스와 같은 참조 타입 필드가 존재한다.
	// 참조형은 주소값이 생기게 되고, clone을 만들면 값이 복제된다.
	
	public DeepClone(String name, int age, int[] scores, Car car) {
		super();
		this.name = name;
		this.age = age;
		this.scores = scores;
		this.car = car;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {

		DeepClone cloned = (DeepClone) super.clone();	// 일반 멤버 변수는 그대로 복제한다.
		// 기본형은 값만 복제되기 때문(교재 466p 아래서 3번째줄 내용.)
		// 하지만 참조형(배열, 클래스 등)은 주소만 복제되기 때문에 새 객체를 형성해준다.
		
		cloned.scores = Arrays.copyOf(this.scores, this.scores.length);
		// 특정한 길이의 어레이를 만들어 기존 값을 통째로 복사하여 객체를 만듬.
		
		cloned.car = new Car(this.car.model);
		// 기존 car의 모델을 매개값 그대로 넣어서 새로 객체를 만듬.
		
		
		return cloned;
	}

	public DeepClone getClone() {
		DeepClone cloned = null;
		
		try {
			cloned = (DeepClone)clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return cloned;
	}
}
