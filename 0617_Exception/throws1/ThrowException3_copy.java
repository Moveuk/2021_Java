package throws1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ThrowException3_copy {

	public static void main(String[] args){

		Scanner scanner = new Scanner(System.in);
		System.out.println("생성할 파일 이름을 입력하세요 : ");
		String fileName = scanner.nextLine();
		try {
			File f = createFile(fileName);
			System.out.println("파일 생성 완료");
		} catch (IOException e) {
			System.out.println(e.getMessage()+1);
		} catch (Exception e) {
			System.out.println(e.getMessage()+2);
		}

	}

	public static File createFile(String fileName) throws IOException,Exception {

		if (fileName == null || fileName.equals("")) {
			throw new Exception("파일의 이름이 유효하지 않습니다.");
		}
		File f = new File(fileName);
		
		f.createNewFile();
		
		return f;
	}

}
