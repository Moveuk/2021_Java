package generic_wildcard;

public class Course<T> {
	private String name;	// 과정 이름
	private T[] students;	// 과정을 수강하는 수강생 이름 배열.
	
	public Course(String name, int capacity) {
		this.name = name;
		// 타입 파라미터로 배열을 생성하려면 new T[n] 형태로 배열을 생성할 수 없고 (T[]) (new Object[n])으로 생성해야 한다.
		students = (T[]) (new Object[capacity]);	
		// 모든 클래스의  부모인 Obj를 생성해서 T로 다시 캐스팅 해주겠다는 의미.
	}

	public String getName() { return name; }
	public T[] getStudents() { return students; }
	public void add(T t) {
		for(int i=0; i<students.length; i++) {
			if(students[i] == null) {
				students[i] = t;
				break;
			}
		}
	}
}
