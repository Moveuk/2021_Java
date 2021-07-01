package function;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.ToIntFunction;

public class FunctionExample2 {
	private static List<Student> list = Arrays.asList(new Student("ȫ�浿", 90, 96), new Student("�ſ��", 95, 93));

	private static List<Student> list2 = Arrays.asList(new Student("������", 100, 97), new Student("������", 95, 93));

	private static List<Student> list3 = Arrays.asList(new Student("������", 100, 97), new Student("������", 95, 93));

	public static double avg(List<Student> list, ToIntFunction<Student> function) { // ���� ������ ���鼭 �� ���� ����.
		int sum = 0;
		for (Student student : list) {
			sum += function.applyAsInt(student);
		}
		double avg = (double) sum / list.size();
		return avg;
	}

	public static double getTotal(List<Student> list, ToIntFunction<Student> function,
			ToIntFunction<Student> function2) {
		int engTotal = 0;
		int mathTotal = 0;

		for (Student student : list) {
			engTotal += function.applyAsInt(student);
		}
		for (Student student : list) {
			mathTotal += function2.applyAsInt(student);
		}
		return engTotal + mathTotal;
	}

	public static void groupAvg(List<Student> list) {
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			Student s = list.get(i);
			int avg = (s.getEnglishScore() + s.getMathScore()) / 2;
			System.out.println((i + 1) + "�� �л� " + s.getName() + "�� ��� ���� : " + avg);
		}

	}

	public static void program() {
		Boolean flag = true;
		Scanner sc = new Scanner(System.in);

		while (flag) {
			System.out.println("���ϴ� ����� �Է����ּ���.");
			System.out.println("1. ���� ������ ���� Ȯ��");
			System.out.println("2. ���� ���� Ȯ��");
			System.out.println("3. ���� �л��� ��� ���� Ȯ��");
			System.out.print(">> \n");
			int num = sc.nextInt();

			switch (num) {
			case 1:
				System.out.println("�� ���� ������ Ȯ���Ͻðڽ��ϱ�? \n >>");
				int grNum = sc.nextInt();
				int listNum = 0;
				if (condition) {
					
				}
				
				System.out.println("� ���� ������ Ȯ���Ͻðڽ��ϱ�?");
				System.out.print("1.���� 2.���� \n >>");
				int subj = sc.nextInt();
				
				avg(list, null)
				break;
			case 2:

				break;
			case 3:

				break;
			default:
				break;
			}

		}

	}

	public static void main(String[] args) {
		double englishAvg = avg(list, s -> s.getEnglishScore());
		System.out.println("1�� ���� ��� ����: " + englishAvg);

		double englishAvg2 = avg(list2, s -> s.getEnglishScore());
		System.out.println("2�� ���� ��� ����: " + englishAvg2);

		double mathAvg = avg(list, s -> s.getMathScore());
		System.out.println("���� ��� ����: " + mathAvg);

		double list1Total = getTotal(list, s -> s.getEnglishScore(), s -> s.getMathScore());
		System.out.println("��Ż ���� : " + list1Total);

		System.out.println("");
		System.out.println("1�� �л��� ���");
		groupAvg(list);

	}
}
