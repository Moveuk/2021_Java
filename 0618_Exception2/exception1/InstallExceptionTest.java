package exception1;

public class InstallExceptionTest {

	public static void main(String[] args) {
		try {
			startInstall();
		} catch (SpaceException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("설치 공간을 확보하고 다시 설치해주세요.");
		} catch (MemoryException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("메모리 공간을 확보하고 다시 설치해주세요.");
		} finally {		// 설치 후 설치 파일 삭제는 오류가 생기던 안생기던 해야하므로 finally를 쓴다.
			deleteFile();
		}
		
	}

	static void startInstall() throws SpaceException, MemoryException{
		
		if (!enoughSpace()) {
			throw new SpaceException("설치공간이 부족합니다.");
		}
		
		if (!enoughMemory()) {
			throw new MemoryException("메모리가 부족합니다.");			
		}
		
	}
	
	static boolean enoughSpace() {	// 프로그램 설치시 저장 공간 조사 메소드
		
		return false;
	}

	static boolean enoughMemory() {	// 프로그램 설치시 메모리 조사 메소드
		
		return true;
	}
	
	static void deleteFile() {
		System.out.println("파일이 삭제 되었습니다.");
	}
}
