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
			new Student("ȫ�浿", "����", 90),
			new Student("�����", "����", 90),
			new Student("���ڹ�", "����",  95),
			new Student("���ѳ�", "����", 92)
	);
	
	// max �Լ� �ϼ�
	// ��� : 4�� �л� ���� �߿� ���� ���� ���� ���.
	// �Լ��� �������̽��� �Ű������� ������ �Լ��� �ۼ��� ��.
	// main �Լ��� max���� ���ٽ��� �ۼ��� ��.
	public static int max(IntBinaryOperator operator) {
		int max = list.get(0).getScore();	// 0 ���� �ص� ��.
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
		System.out.println("�ְ�����: " + max);
		System.out.println("�ְ�����: " + max2);
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
