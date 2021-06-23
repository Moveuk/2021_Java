
public class CalcTest {

	public static void main(String[] args) {
		
		// 0603_5교시 정적 멤버 수업 -----------------------------------
		double result = Calc.pi * 10 * 10;
		System.out.println(result);
		
		sum = Calc.plus(10,20);
		System.out.println(sum);
		
		Calc c1 = new Calc();
		Calc c2 = new Calc();
		
		c1.iv = 100;
		System.out.println(c1.iv);
		System.out.println(c2.iv);
		
		System.out.println("변경 전 c1.pi 값 : "+c1.pi);
		c1.pi = 123.456;
		System.out.println("변경 전 c1.pi 값 : "+c1.pi);
		System.out.println("변경 전 c2.pi 값 : "+c2.pi);
		System.out.println("static으로 고정하면 값이 같이 변함.");
		
		System.out.println("변경 불가능한 c.PI 값 : "+c.PI);
		System.out.println("final 대문자로 다른곳에서 못바꿈.");
		System.out.println("------상수 변경 방법.----------");
		//
		
		Calc c3 = new Calc(0.98);
		
		System.out.println("초기값 \t\t\t: "+c.GRAVITY);
		System.out.println("생성자 생성시 설정한 초기값.\t : "+c3.GRAVITY);
		
	}

}
