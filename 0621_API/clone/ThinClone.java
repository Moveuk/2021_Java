package clone;

// 복제를 위한 Member2

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
	
	public ThinClone getMember() {	// 복제를 해주는 메소드.
		ThinClone cloend = null;
		
		try {
			cloend= (ThinClone)clone(); // Cloneable이 없으면 예외가 생김 그래서 예외 처리를 해야함.
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}	
		
		return cloend;
	}
}
