package clone;

public class DeepCloneTest {

	public static void main(String[] args) {

		DeepClone original = new DeepClone("ȫ�浿", 25, new int[] {90,90}, new Car("�ҳ�Ÿ"));
		
		DeepClone cloned = original.getClone();
		
		// ���纻�� ���� ����
		cloned.scores[0] = 100;
		cloned.car.model = "�׷���";
		
		// ������ ���纻�� ���� ���� ���� ����Ʈ
		
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
