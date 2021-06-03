// 이것이 자바다 _ 신용권 : 203p ~
//--------------------------------------------- 

public class CalcTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calc c = new Calc(); // 생성자가 없어도 가능함.
		
		c.add(); // System.out.println(3+5); 들어잇음
		
		c.add(20, 30); // System.out.println(x+y);
	
		c.addInt(20, 30); // sum이라는게 선언되는 것이 아니라 디버그 해보면 add2() return 값으로 보임.
		System.out.println(c.addInt(20, 30));
		int sum = c.addInt(70, 80); // 변수에 선언도 가능함.
		System.out.println(sum);
	
		double sumDouble = c.addDouble(10.5, 1.1235);
		System.out.println(sumDouble);
	
		System.out.println("c.subs의 결과 :\t\t"+c.subs(5, 4));
		System.out.println("c.multiply의 결과 :\t"+c.multiply(5, 4));
		System.out.println("c.divide의 결과 :\t\t"+c.divide(5, 4));
		
		
		// 가변형 매개 변수 메소드의 호출.
		int s1 = c.sum(1,2,3);
		System.out.println(s1);
		int s2 = c.sum(1,2,3,4,5,6,7,8,9,10);
		System.out.println(s2);

	}

}
