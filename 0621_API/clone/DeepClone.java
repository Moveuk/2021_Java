package clone;

import java.util.Arrays;

public class DeepClone implements Cloneable{

	public String name;
	public int age;
	public int[] scores;	// ���� ������ �޸� �迭,
	public Car car;			// Ŭ������ ���� ���� Ÿ�� �ʵ尡 �����Ѵ�.
	// �������� �ּҰ��� ����� �ǰ�, clone�� ����� ���� �����ȴ�.
	
	public DeepClone(String name, int age, int[] scores, Car car) {
		super();
		this.name = name;
		this.age = age;
		this.scores = scores;
		this.car = car;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {

		DeepClone cloned = (DeepClone) super.clone();	// �Ϲ� ��� ������ �״�� �����Ѵ�.
		// �⺻���� ���� �����Ǳ� ����(���� 466p �Ʒ��� 3��°�� ����.)
		// ������ ������(�迭, Ŭ���� ��)�� �ּҸ� �����Ǳ� ������ �� ��ü�� �������ش�.
		
		cloned.scores = Arrays.copyOf(this.scores, this.scores.length);
		// Ư���� ������ ��̸� ����� ���� ���� ��°�� �����Ͽ� ��ü�� ����.
		
		cloned.car = new Car(this.car.model);
		// ���� car�� ���� �Ű��� �״�� �־ ���� ��ü�� ����.
		
		
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
