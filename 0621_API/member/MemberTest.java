package member;

public class MemberTest {

	public static void main(String[] args) {

		Member m1 = new Member("123");	// ���̵� ���� ����� �������� �ʴ´�.
		Member m2 = new Member("123");	// �ֹι�ȣ�� ���� PK�� �ϳ��� �����ؾ��Ѵ�.
		
		// �׷��Ƿ� ������ ���� Ȯ�����ش�.
		
		if (m1==m2) {	// ������ڴ� �ּҰ��� ���ϴ� ���̱� ������ m1,m2 ������ ���� �ּҰ��� �޶�����.(���� ���� �����Ͱ� ������.)
			System.out.println("m1�� m2�� ����.");
		}

		if (m1.equals(m2)) {	// equals�� Object�� �޼ҵ�� �Ű������� ���� ��� ���� �ڵ� ����ȯ�� �Ͼ��.
			System.out.println("m1�� m2�� ����.");			
		}	// �������̵� ������ ���� �ʾƼ� ������ ����
		// ���� equals�� ��� ������ ���������� ���ϴ� �޼ҵ��̴�. (���̳���)
		
		System.out.println("--------hashcode ���� ��-------");
		
		System.out.println(m1.hashCode());
		System.out.println(m2.hashCode());
		// �ؽ��ڵ尡 �ٸ���. ������ �������̵带 ���� �� ��ü�� ���� �ؽ��ڵ带 ������ ���� �� �ִ�.
	}
}
