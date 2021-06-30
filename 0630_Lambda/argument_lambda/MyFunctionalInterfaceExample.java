package argument_lambda;

public class MyFunctionalInterfaceExample {
	public static void main(String[] args) {
		MyFunctionalInterface fi;
		
		fi= (x,y) -> x + y;
		
		// alter : 리턴문이 있는 경우
//		fi= (x,y) -> { 
//			return x + y;
//		};
		
		System.out.println(fi.method(10,20));

		System.out.println(fi.method(20,30));
		
		System.out.println(fi.method(30,40));
		
		fi= (x,y) -> sum(x, y);
		
		System.out.println(fi.method(100, 23));
		
	}
	
	public static int sum(int x, int y) {
		return x + y;
	}
}
