package datecalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateExample {

	public static void main(String[] args) {		// 11.14 Date Ŭ����
		
		Date d = new Date();	//����Ʈ ���ǻ��� : import java.util.Date; �� �ؾ���
		
		System.out.println(d);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM�� dd�� hh��");	// �ش� ����� ������ ����.
		
		sdf.format(d); // sdf�� ������� ��ȯ����.
		
		System.out.println(sdf.format(d));
		// �� (���α׷��� 1900���� ����)
		System.out.println(d.getYear());
		System.out.println(1900+d.getYear()+"��");
		// �� (���α׷��� 0������ ����)
		System.out.println(d.getMonth());
		System.out.println(1+d.getMonth()+"��");
		
		
		Calendar now = Calendar.getInstance(); 
		// �ڱ� Ÿ���� �޼ҵ带 ȣ���Ѵ�. Private���� ���� �Ǿ��־ �ν��Ͻ��� ������� �����̴�.
		
		System.out.println(now);
		
		System.out.println(now.get(Calendar.YEAR));
		// Calender���� �پ��� ���(final)������ �����Ѵ�. YEAR, May, HOUR, MIN ���..

	}

}
