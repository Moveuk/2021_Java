package exercise;

import java.util.function.ToIntFunction;

public class LambdaExample_6 {
	
	private static Student[] students = {
			new Student("홍길동", 90, 96),
			new Student("신용권", 95, 93)
	};
			
	// avg() 메소드 작성
	public static double avg(ToIntFunction<Student> function) {
		double sum = 0;
		for (Student student : students) {
			sum += function.applyAsInt(student);
		}
		return sum / students.length;
	}
	

	public static void main(String[] args) {
		// 매개 변수의 메소드 참조 형으로 바꾸어보았다. (교재 714p)
		double englichAvg = avg( Student :: getEnglishScore);
		System.out.println("영어 평균 점수 : "+englichAvg);
		double mathAvg = avg( s -> s.getMathScore());
		System.out.println("수학 평균 점수 : "+mathAvg);
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
