package datecalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateExample {

	public static void main(String[] args) {		// 11.14 Date 클래스
		
		Date d = new Date();	//임포트 주의사항 : import java.util.Date; 을 해야함
		
		System.out.println(d);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시");	// 해당 양식이 정해져 있음.
		
		sdf.format(d); // sdf의 양식으로 변환해줌.
		
		System.out.println(sdf.format(d));
		// 년 (프로그램은 1900부터 시작)
		System.out.println(d.getYear());
		System.out.println(1900+d.getYear()+"년");
		// 월 (프로그램은 0월부터 시작)
		System.out.println(d.getMonth());
		System.out.println(1+d.getMonth()+"월");
		
		
		Calendar now = Calendar.getInstance(); 
		// 자기 타입의 메소드를 호출한다. Private으로 정의 되어있어서 인스턴스를 못만들기 때문이다.
		
		System.out.println(now);
		
		System.out.println(now.get(Calendar.YEAR));
		// Calender에는 다양항 상수(final)값들이 존재한다. YEAR, May, HOUR, MIN 등등..

	}

}
