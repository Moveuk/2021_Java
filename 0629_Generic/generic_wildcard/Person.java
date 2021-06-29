package generic_wildcard;

public class Person {
	private String name;
	
	public Person(String name) {
		this.name = name;
	}

//	public String getName() { return name; } // 없어도되는데 무슨 용도인지 모르겠음.
	public String toString() { return name; }
}
