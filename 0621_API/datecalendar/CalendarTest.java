package datecalendar;

import java.util.Calendar;
import java.util.Scanner;

public class CalendarTest {

	public static void main(String[] args) {
		int endDay;
		int startWeek;

		// �޷� �����
		Scanner sc = new Scanner(System.in);

		System.out.println("�⵵ �Է� : ");
		int year = sc.nextInt();
		System.out.println("�� �Է� : ");
		int month = sc.nextInt();

		
		Calendar sDay = Calendar.getInstance(); // �̱��� ���� �ϳ��� �ν��Ͻ��� �����ؼ� ���.
		Calendar eDay = Calendar.getInstance();
		Calendar fDay = Calendar.getInstance(); // ���� ���ϱ� ���� �ν��Ͻ�
		
		// ���� startDay, endDay ����
		sDay.set(year, month - 1, 1); // �츮�� �Է��� �޿��� -1�� �ؾ� ��ǻ�Ͱ� �����ϴ� �� ���� �ȴ�.
		// �ش� ����, �ش� ���� 1�� ��¥�� ������.
		eDay.set(year, month, 1);
		// ���� �� 1�Ϸ� ������.

		eDay.add(Calendar.DATE, -1); // eDay�� ��¥���� ����(Calendar.DATE)�� -1�� ��.)
		// �� ���� ������ ��¥�� ���ϱ� ����.(�� �޸��� ������ ��¥�� �ٸ��� ������ ���� ���ϱ� ���Ͽ� �̷��� �����Ѵ�.)

		startWeek = sDay.get(Calendar.DAY_OF_WEEK); // 1���� ���� ������ ����.
		endDay = eDay.get(Calendar.DAY_OF_MONTH); // ���� ������ ��¥�� ����.

		// �޷� ��� ���
		System.out.println("   " + year + "�� " + month + "��");
		System.out.println(" Su Mo Tu We Th Fr Sa ");

		for (int i = 1; i <= (startWeek-1); i++) {	// startWeek �״�� ���� ���ִ� ��ġ���� ���� ä��Ƿ� -1 ������
			System.out.print("   ");
		}

		for (int i = 1,s = startWeek; i <= endDay; i++,s++) {
			System.out.print(i < 10 ? "  "+i : " "+i);	//10���� ������ ���� 2ĭ 10���� ���� ������ ���� 1ĭ
			if (s%7==0) {	// s�� 7�� ����� �ٳѱ�.
				System.out.println("");
			}
		}
		
		// KeyPoint : for���� �ʱⰪ�� 
		
		// ���� ����
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
		System.out.println("---�޷� �մ� �޴� ä���---");
		System.out.println("");
		
		// �޷��� ��κ��� �� �ް� �մ��� ��¥�� ä����.
		
		// ���� ���ϱ�
		fDay.set(year, month - 1, 1);
		fDay.add(Calendar.DATE, -startWeek);	// ���� ��������
		int formerEndDay = fDay.get(Calendar.DAY_OF_MONTH);
		// ������ ���ϱ� : eDay + 1 �ϸ� ������ 1��
		eDay.add(Calendar.DATE, 1);
		int nextStartDay = eDay.get(Calendar.DAY_OF_MONTH);
		
		
		System.out.println("   " + year + "�� " + month + "��");
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
