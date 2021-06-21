package clone;

// ������ ���� Member2

public class ThinClone implements Cloneable{

	public String id;
	public String name;
	public String password;
	public int age;
	public boolean adult;
	
	public ThinClone(String id, String name, String password, int age, boolean adult) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.adult = adult;
	}
	
	public ThinClone getMember() {	// ������ ���ִ� �޼ҵ�.
		ThinClone cloend = null;
		
		try {
			cloend= (ThinClone)clone(); // Cloneable�� ������ ���ܰ� ���� �׷��� ���� ó���� �ؾ���.
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}	
		
		return cloend;
	}
}
