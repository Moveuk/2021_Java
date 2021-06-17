package trycatchfinally;

public class NumberFormatExceptionExample {
	public static void main(String[] args) {
		String data1 = "100";
		String data2 = "a100";
		String string = null;
				
		int value1 = Integer.parseInt(data1);
		int value2 = 0;
		try {
//			System.out.println(string.toString());	// NullPointException 이 catch에 없지만 마지막 Exception이 잡아줌.
//			System.out.println(value1 / 0);		// catch문 조건에서 예외 처리 타입이 다르면 처리하지 못한다. 그래서 다른 catch문을 추가한다.(다중catch문)
			value2 = Integer.parseInt(data2);
		} catch (ArithmeticException | NumberFormatException e) {		// 멀티 catch문	
			e.printStackTrace();  				// 예외가 어떤 과정을 통해 발생했는지.
			System.out.println(e.getMessage() + " : 정수를 0으로 나누시면 안됩니다.");
		} catch (Exception e) {					// 가장 상위 예외 클래스이므로 모든 예외를 받아준다. 
			System.out.println(e.getMessage() + " : 예외가 발생했습니다.");
		} finally {				// 예외와는 별개로 무조건 한번은 실행해야 할 때 작성
			System.out.println("예외 처리에 대한 실행을 완료합니다.");
		}
		
		int result = value1 + value2;
		System.out.println(data1 + "+" + data2 + "=" + result);
	}
}

