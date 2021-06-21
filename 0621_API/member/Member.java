package member;

public class Member {
	
	public String id;

	public Member(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
// ��� �Ű��� id�� ���������� ���ϴ� ������ �޼ҵ带 �������Ѵ�.
		if (obj instanceof Member) {
			
			Member other = (Member)obj;
			
			return this.id.equals(other.id);
			
		}
			return false;	// �ݵ�� if ���� �ܺο� return�� �־�� ���α׷����� Ÿ�� ���� �ν��Ѵ�.
	}

	@Override
	public int hashCode() {
		return id.hashCode();	// id���� �ؽ��ڵ�� ���� ���ѹ�����.
	}

}


//int a = 10;
//
//int b = 10;
//
//b = a;