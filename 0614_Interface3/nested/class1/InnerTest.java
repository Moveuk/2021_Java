package nested.class1;

public class InnerTest {

	public static void main(String[] args) {

		A a = new A();
		
		A.B b = a.new B();  //�ν��Ͻ��� ����Ŭ������ �̷��� ���� �ؾ���.
		
		b.field = 3;
		b.method1();
		
		//------------------------------
		
		A.C c = new A.C();	// ���� ���� Ŭ����.
		c.field = 3;
		c.method1();
		A.C.field2 = 3 ; 	// ����ƽ�� �����̱� ������ �ν��Ͻ�ȭ ���� �ʾƵ� ȣ�� ������.
		A.C.method2(); 		// ����ƽ �޼ҵ�� ������ �ٷ� ȣ�� ����.

		//------------------------------
		
		a.method(); 		// ���� ���� Ŭ����
		// ���� Ŭ�������� ���� ��Ű�� �ڵ�.
	}

}
