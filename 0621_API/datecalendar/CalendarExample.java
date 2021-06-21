package datecalendar;

import java.util.*;

public class CalendarExample {
	public static void main(String[] args) {
		Calendar now = Calendar.getInstance();
		
		int year    = now.get(Calendar.YEAR);			// YEAR ��� �ޱ�.          
		int month  = now.get(Calendar.MONTH) + 1;		// MONTH ��� + 1(0�������̹Ƿ�) 
		int day    = now.get(Calendar.DAY_OF_MONTH);	// DAY_OF_MONTH ��¥ 21 ���

		int week    = now.get(Calendar.DAY_OF_WEEK);	// DAY_OF_WEEK ���� ��� ���
		String strWeek = null;
		switch(week) {									// ��� ���� ��� ���ڿ��� ��ȯ.
			case Calendar.MONDAY:	//2
				strWeek = "��";
				break;
			case Calendar.TUESDAY:	//3
				strWeek = "ȭ";
				break;
			case Calendar.WEDNESDAY://4
				strWeek = "��";
				break;
			case Calendar.THURSDAY:	//5
				strWeek = "��";
				break;
			case Calendar.FRIDAY:	//6
				strWeek = "��";
				break;
			case Calendar.SATURDAY:	//7
				strWeek = "��";
				break;
			default:				//1
				strWeek = "��";
		}
		
		int amPm  = now.get(Calendar.AM_PM);   // ���� ���� �� ��ȯ
		String strAmPm = null;
		if(amPm == Calendar.AM) {
			strAmPm = "����";
		} else {
			strAmPm = "����";
		}
		
		int hour    = now.get(Calendar.HOUR);	// �ð� �� ��ȯ
		int minute  = now.get(Calendar.MINUTE);	// �� �� ��ȯ
		int second  = now.get(Calendar.SECOND);	// �� �� ��ȯ   

		//���
		System.out.print(year + "�� ");
		System.out.print(month + "�� ");
		System.out.println(day + "�� ");
		
		System.out.print(strWeek + "���� ");
		System.out.println(strAmPm + " ");
		
		System.out.print(hour + "�� ");
		System.out.print(minute + "�� ");
		System.out.println(second + "�� ");
	}
}
