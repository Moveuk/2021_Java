package set2;

public class Member {

	public String name;
	public int age;
	
	public Member(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public int hashCode() {
		// 만약 같은 이름과 나이라면 해쉬코드가 같을 거고 그러면 HashSet 흐름도에서 equals에게 보내서 같은지 확인한다.
		return (name+age).hashCode(); 
	}

	@Override
	public boolean equals(Object obj) {
		Member member = (Member)obj;
		
		boolean result = (this.name == member.name) && (this.age == member.age);
		
		return result;
	}

	
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + age;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Member other = (Member) obj;
//		if (age != other.age)
//			return false;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		return true;
//	}
}
