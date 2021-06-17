package throws1;

public class ThrowException2 {

	public static void main(String[] args) /*throws Exception*/ {
		// 또 다시 예외를 떠넘길 수 있지만 이는 바람직한 예외 처리 방법이 아님.
		
		try {
			method1(); 				// method1 호출하려면 예외 처리해야함(try-catch). 근데 또 예외를 넘김.		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void method1() throws Exception{ // 또 예외 만듬. 또 문제를 넘김. 
		method2();
		throw new ArithmeticException("수리적 예외가 발생!");	// 앞에서 예외 발생해서 못건들임.
	}

	public static void method2() throws Exception {	// 예외를 던져놓음. 쓰는 사람이 처리하게 내비둠.
		throw new Exception("예외가 발생!");
	}
}
