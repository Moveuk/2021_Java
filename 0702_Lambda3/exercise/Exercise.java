package exercise;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class Exercise {
	private static List<Student> list = Arrays.asList(
			new Student("홍길동", "남자", 90),
			new Student("김순희", "여자", 90),
			new Student("감자바", "남자",  95),
			new Student("박한나", "여자", 92)
	);
	
	// max 함수 완성
	// 기능 : 4명 학생 점수 중에 제일 높은 점수 출력.
	// 함수형 인터페이스를 매개변수로 가지는 함수를 작성할 것.
	// main 함수의 max에는 람다식을 작성할 것.
	public static int max(IntBinaryOperator operator) {
		int max = list.get(0).getScore();	// 0 으로 해도 됨.
		for (Student student : list) {
			max = operator.applyAsInt(max, student.getScore());
		}
		return max;
	}
	
	public static int max2(ToIntFunction<Student> function) {
		int max = 0;
		int score = 0;
		
		for (Student student : list) {
			score = function.applyAsInt(student);
			if (max < score) {
				max = score;
			}
		}
		return max;
	}
	
	
	public static void main(String[] args) {
		
		
		int max2 = max2(s -> s.getScore());
		
		int max = max((a,b) -> {
			if (a >= b) {
				return a;
			}
			return b;
		});
		System.out.println("최고점수: " + max);
		System.out.println("최고점수: " + max2);
	}

	public static class Student {
		private String name;
		private String sex;
		private int score;
		
		public Student(String name, String sex, int score) {
			this.name = name;
			this.sex = sex;
			this.score = score;
		}

		public String getSex() { return sex; }
		public int getScore() { return score; }
	}
	
}
