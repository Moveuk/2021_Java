package datecalendar;

import java.util.Calendar;
import java.util.Scanner;

public class CalendarTest {

	public static void main(String[] args) {
		int endDay;
		int startWeek;

		// 달력 만들기
		Scanner sc = new Scanner(System.in);

		System.out.println("년도 입력 : ");
		int year = sc.nextInt();
		System.out.println("월 입력 : ");
		int month = sc.nextInt();

		
		Calendar sDay = Calendar.getInstance(); // 싱글턴 패턴 하나의 인스턴스로 공유해서 사용.
		Calendar eDay = Calendar.getInstance();
		Calendar fDay = Calendar.getInstance(); // 전달 구하기 위한 인스턴스
		
		// 달의 startDay, endDay 설정
		sDay.set(year, month - 1, 1); // 우리가 입력한 달에서 -1을 해야 컴퓨터가 이해하는 월 값이 된다.
		// 해당 연도, 해당 월의 1일 날짜로 세팅함.
		eDay.set(year, month, 1);
		// 다음 달 1일로 세팅함.

		eDay.add(Calendar.DATE, -1); // eDay의 날짜정보 연산(Calendar.DATE)에 -1을 함.)
		// 전 월의 마지막 날짜를 구하기 위함.(매 달마다 마지막 날짜가 다르기 때문에 쉽게 구하기 위하여 이렇게 접근한다.)

		startWeek = sDay.get(Calendar.DAY_OF_WEEK); // 1일의 시작 요일을 구함.
		endDay = eDay.get(Calendar.DAY_OF_MONTH); // 달의 마지막 날짜를 구함.

		// 달력 모양 잡기
		System.out.println("   " + year + "년 " + month + "월");
		System.out.println(" Su Mo Tu We Th Fr Sa ");

		for (int i = 1; i <= (startWeek-1); i++) {	// startWeek 그대로 쓰면 값넣는 위치까지 공백 채우므로 -1 시켜줌
			System.out.print("   ");
		}

		for (int i = 1,s = startWeek; i <= endDay; i++,s++) {
			System.out.print(i < 10 ? "  "+i : " "+i);	//10보다 작으면 공백 2칸 10보다 작지 않으면 공백 1칸
			if (s%7==0) {	// s가 7의 배수면 줄넘김.
				System.out.println("");
			}
		}
		
		// KeyPoint : for문의 초기값을 
		
		// 나의 오답
//		int i = 1;
//		for (int x = 0; x < 5; x++) {
//			for (int j = 1; k < 7*x; j++) {
//				while (i < startWeek) {
//					System.out.print("   ");
//					i++;
//				}
//				for (i =1; i < 8; i++) {
//					System.out.print("  " + k++);
//				}
//			}
//			System.out.println("");
//		}
		
		System.out.println("");
		System.out.println("---달력 앞달 뒷달 채우기---");
		System.out.println("");
		
		// 달력의 빈부분을 전 달과 앞달의 날짜로 채워라.
		
		// 전달 구하기
		fDay.set(year, month - 1, 1);
		fDay.add(Calendar.DATE, -startWeek);	// 전달 마지막날
		int formerEndDay = fDay.get(Calendar.DAY_OF_MONTH);
		// 다음달 구하기 : eDay + 1 하면 다음달 1일
		eDay.add(Calendar.DATE, 1);
		int nextStartDay = eDay.get(Calendar.DAY_OF_MONTH);
		
		
		System.out.println("   " + year + "년 " + month + "월");
		System.out.println(" Su Mo Tu We Th Fr Sa ");

		int sValue = 0;
		for (int i = 1; i < startWeek; i++) {
			System.out.print((formerEndDay+i) < 10 ? "  "+ (formerEndDay+i): " "+(formerEndDay+i));
		}

		for (int i = 1,s = startWeek; i <= endDay; i++,s++) {
			System.out.print(i < 10 ? "  "+i : " "+i);
			if (s%7==0) {
				System.out.println("");
			}
			sValue = s%7;
		}
		
		if (0<sValue) {
			for (int i = 0; i < (7-sValue); i++) {
				System.out.print("  "+ (nextStartDay+i));
			}
		}
		System.out.println("");
		System.out.println("----------------------");
	}

}
