package throws1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ThrowException3 {

	public static void main(String[] args) /*throws Exception*/ {

		Scanner scanner = new Scanner(System.in);
		System.out.println("생성할 파일 이름을 입력하세요 : ");
		String fileName = scanner.nextLine();
		
		File f = createFile(fileName);
		System.out.println("파일 생성 완료");
		
	}
	
	public static File createFile(String fileName) {
		
		try {
			if (fileName == null || fileName.equals("")) {
				throw new Exception("파일의 이름이 유효하지 않습니다.");
			}			
		} catch (Exception e) {
			fileName = "제목없음.txt";
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
			System.out.println("파일 생성에 문제가 발생했습니다.");
		}
	}
}
