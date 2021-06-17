package throws1;

public class ThrowException {

	public static void main(String[] args) {
		
		try {	// 강제 예외 발생
			throw new Exception("고의로 예외발생");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage()); // 고의로 예외 발생이 나오게 됨.
		} finally {
			
		}
	}
}
