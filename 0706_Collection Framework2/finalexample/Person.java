package finalexample;

public class Person implements Comparable<Person> {
	public String name;
	public int age;
	public String address;

//	public Person(String name, int age) {
//		super();
//		this.name = name;
//		this.age = age;
//	}

	
	public Person(String name, int age, String address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}

	@Override
	public int compareTo(Person o) {
		// String ����
		return address.compareTo(o.address); // ��������
//		return o.address.compareTo(address); // ��������
		// ���� ����
//		return age - o.age; // ��������
//		return o.age - age;	// ��������
	}
}
