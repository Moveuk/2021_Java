package function;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.ToIntFunction;

public class FunctionExample2 {
	private static List<Student> list = Arrays.asList(new Student("홍길동", 90, 96), new Student("신용권", 95, 93));

	private static List<Student> list2 = Arrays.asList(new Student("조영민", 100, 97), new Student("음동원", 95, 93));

	private static List<Student> list3 = Arrays.asList(new Student("조영민", 100, 97), new Student("음동원", 95, 93));

	public static double avg(List<Student> list, ToIntFunction<Student> function) { // 향상된 포문을 돌면서 총 점수 구함.
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
			System.out.println((i + 1) + "번 학생 " + s.getName() + "의 평균 점수 : " + avg);
		}

	}

	public static void program() {
		Boolean flag = true;
		Scanner sc = new Scanner(System.in);

		while (flag) {
			System.out.println("원하는 기능을 입력해주세요.");
			System.out.println("1. 반의 교과별 점수 확인");
			System.out.println("2. 반의 총점 확인");
			System.out.println("3. 반의 학생별 평균 점수 확인");
			System.out.print(">> \n");
			int num = sc.nextInt();

			switch (num) {
			case 1:
				System.out.println("몇 반의 점수를 확인하시겠습니까? \n >>");
				int grNum = sc.nextInt();
				int listNum = 0;
				if (condition) {
					
				}
				
				System.out.println("어떤 교과 점수를 확인하시겠습니까?");
				System.out.print("1.영어 2.수학 \n >>");
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
		System.out.println("1반 영어 평균 점수: " + englishAvg);

		double englishAvg2 = avg(list2, s -> s.getEnglishScore());
		System.out.println("2반 영어 평균 점수: " + englishAvg2);

		double mathAvg = avg(list, s -> s.getMathScore());
		System.out.println("수학 평균 점수: " + mathAvg);

		double list1Total = getTotal(list, s -> s.getEnglishScore(), s -> s.getMathScore());
		System.out.println("토탈 점수 : " + list1Total);

		System.out.println("");
		System.out.println("1반 학생별 평균");
		groupAvg(list);

	}
}
