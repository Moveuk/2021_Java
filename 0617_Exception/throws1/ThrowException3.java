package throws1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ThrowException3 {

	public static void main(String[] args) /*throws Exception*/ {

		Scanner scanner = new Scanner(System.in);
		System.out.println("������ ���� �̸��� �Է��ϼ��� : ");
		String fileName = scanner.nextLine();
		
		File f = createFile(fileName);
		System.out.println("���� ���� �Ϸ�");
		
	}
	
	public static File createFile(String fileName) {
		
		try {
			if (fileName == null || fileName.equals("")) {
				throw new Exception("������ �̸��� ��ȿ���� �ʽ��ϴ�.");
			}			
		} catch (Exception e) {
			fileName = "�������.txt";
		} finally {
			File f = new File(fileName);
			create(f);
			return f;
		}
	}
	
	static void create(File f) {
		try {
			f.createNewFile();
		} catch (IOException e) {
			System.out.println("���� ������ ������ �߻��߽��ϴ�.");
		}
	}
}
