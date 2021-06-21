package clone;

public class DeepCloneTest {

	public static void main(String[] args) {

		DeepClone original = new DeepClone("홍길동", 25, new int[] {90,90}, new Car("소나타"));
		
		DeepClone cloned = original.getClone();
		
		// 복사본의 내용 변경
		cloned.scores[0] = 100;
		cloned.car.model = "그랜져";
		
		// 기존과 복사본의 내용 변경 차이 프린트
		
		System.out.println("-------original-----");
		System.out.println(original.scores[0]);
		System.out.println(original.scores[1]);
		System.out.println(original.car.model);
		System.out.println("-------------------");
		System.out.println("-------cloned------");
		System.out.println(cloned.scores[0]);
		System.out.println(cloned.scores[1]);
		System.out.println(cloned.car.model);
	}
}
