package exception1;

public class InstallExceptionTest {

	public static void main(String[] args) {
		try {
			startInstall();
		} catch (SpaceException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("��ġ ������ Ȯ���ϰ� �ٽ� ��ġ���ּ���.");
		} catch (MemoryException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("�޸� ������ Ȯ���ϰ� �ٽ� ��ġ���ּ���.");
		} finally {		// ��ġ �� ��ġ ���� ������ ������ ����� �Ȼ���� �ؾ��ϹǷ� finally�� ����.
			deleteFile();
		}
		
	}

	static void startInstall() throws SpaceException, MemoryException{
		
		if (!enoughSpace()) {
			throw new SpaceException("��ġ������ �����մϴ�.");
		}
		
		if (!enoughMemory()) {
			throw new MemoryException("�޸𸮰� �����մϴ�.");			
		}
		
	}
	
	static boolean enoughSpace() {	// ���α׷� ��ġ�� ���� ���� ���� �޼ҵ�
		
		return false;
	}

	static boolean enoughMemory() {	// ���α׷� ��ġ�� �޸� ���� �޼ҵ�
		
		return true;
	}
	
	static void deleteFile() {
		System.out.println("������ ���� �Ǿ����ϴ�.");
	}
}
