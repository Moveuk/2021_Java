package nested.class1;

public class Outter {
	String field = "Outter-field";
	void method() {
		System.out.println("Outter-method");
	}
	
	class Nested {
		String field = "Nested-field";
		void method() {
			System.out.println("Nested-method");
		}
		void print() {
			System.out.println(this.field);			// ��ø ��ü(Nested) ����.
			this.method();
			System.out.println(Outter.this.field);	// �ٱ� ��ü(Outter) ����.
			Outter.this.method();
		}
	}
}
