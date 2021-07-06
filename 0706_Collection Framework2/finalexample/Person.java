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
		// String 정렬
		return address.compareTo(o.address); // 오름차순
//		return o.address.compareTo(address); // 오름차순
		// 숫자 정렬
//		return age - o.age; // 오름차순
//		return o.age - age;	// 내림차순
	}
}
