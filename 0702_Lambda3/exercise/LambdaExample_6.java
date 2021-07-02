package exercise;

import java.util.function.ToIntFunction;

public class LambdaExample_6 {
	
	private static Student[] students = {
			new Student("ȫ�浿", 90, 96),
			new Student("�ſ��", 95, 93)
	};
			
	// avg() �޼ҵ� �ۼ�
	public static double avg(ToIntFunction<Student> function) {
		double sum = 0;
		for (Student student : students) {
			sum += function.applyAsInt(student);
		}
		return sum / students.length;
	}
	

	public static void main(String[] args) {
		// �Ű� ������ �޼ҵ� ���� ������ �ٲپ�Ҵ�. (���� 714p)
		double englichAvg = avg( Student :: getEnglishScore);
		System.out.println("���� ��� ���� : "+englichAvg);
		double mathAvg = avg( s -> s.getMathScore());
		System.out.println("���� ��� ���� : "+mathAvg);
	}

	public static class Student {
		private String name;
		private int englishScore;
		private int mathScore;
		
		public Student(String name, int englishScore, int mathScore) {
			this.name = name;
			this.englishScore = englishScore;
			this.mathScore = mathScore;
		}

		public String getName() { return name; }
		public int getEnglishScore() { return englishScore; }
		public int getMathScore() { return mathScore; }
	}
}
