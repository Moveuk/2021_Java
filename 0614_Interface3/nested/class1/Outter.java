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
			System.out.println(this.field);			// ÁßÃ¸ °´Ã¼(Nested) ÂüÁ¶.
			this.method();
			System.out.println(Outter.this.field);	// ¹Ù±ù °´Ã¼(Outter) ÂüÁ¶.
			Outter.this.method();
		}
	}
}
