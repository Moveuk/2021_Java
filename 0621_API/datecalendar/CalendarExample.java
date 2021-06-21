package datecalendar;

import java.util.*;

public class CalendarExample {
	public static void main(String[] args) {
		Calendar now = Calendar.getInstance();
		
		int year    = now.get(Calendar.YEAR);			// YEAR 상수 받기.          
		int month  = now.get(Calendar.MONTH) + 1;		// MONTH 상수 + 1(0월부터이므로) 
		int day    = now.get(Calendar.DAY_OF_MONTH);	// DAY_OF_MONTH 날짜 21 출력

		int week    = now.get(Calendar.DAY_OF_WEEK);	// DAY_OF_WEEK 요일 상수 출력
		String strWeek = null;
		switch(week) {									// 상수 변수 출력 문자열로 반환.
			case Calendar.MONDAY:	//2
				strWeek = "월";
				break;
			case Calendar.TUESDAY:	//3
				strWeek = "화";
				break;
			case Calendar.WEDNESDAY://4
				strWeek = "수";
				break;
			case Calendar.THURSDAY:	//5
				strWeek = "목";
				break;
			case Calendar.FRIDAY:	//6
				strWeek = "금";
				break;
			case Calendar.SATURDAY:	//7
				strWeek = "토";
				break;
			default:				//1
				strWeek = "일";
		}
		
		int amPm  = now.get(Calendar.AM_PM);   // 오전 오후 값 반환
		String strAmPm = null;
		if(amPm == Calendar.AM) {
			strAmPm = "오전";
		} else {
			strAmPm = "오후";
		}
		
		int hour    = now.get(Calendar.HOUR);	// 시간 값 반환
		int minute  = now.get(Calendar.MINUTE);	// 분 값 반환
		int second  = now.get(Calendar.SECOND);	// 초 값 반환   

		//출력
		System.out.print(year + "년 ");
		System.out.print(month + "월 ");
		System.out.println(day + "일 ");
		
		System.out.print(strWeek + "요일 ");
		System.out.println(strAmPm + " ");
		
		System.out.print(hour + "시 ");
		System.out.print(minute + "분 ");
		System.out.println(second + "초 ");
	}
}
