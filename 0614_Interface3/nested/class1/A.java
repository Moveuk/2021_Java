package nested.class1;

public class A {
	A() {
		System.out.println("A按眉 积己");
	}
	
	class B{
		B() {
			System.out.println("B按眉 积己");
		}
		
		int field;
		void method1() {
			
		}
	}
	static class C {
		C() {
			System.out.println("static茄 C按眉 积己");
		}
		
		int field;
		static int field2;
		void method1() {
			
		}
		static void method2() {
			
		}
		
	}
	
	void method() {
		class D {
			D() {
				System.out.println("void茄 D按眉 积己");
			}
			int field;
			void method() {
				
			}
		}
		D d = new D();
		d.field = 3;
		d.method();
	}
}
