package generic_wildcard;

public class Course<T> {
	private String name;	// ���� �̸�
	private T[] students;	// ������ �����ϴ� ������ �̸� �迭.
	
	public Course(String name, int capacity) {
		this.name = name;
		// Ÿ�� �Ķ���ͷ� �迭�� �����Ϸ��� new T[n] ���·� �迭�� ������ �� ���� (T[]) (new Object[n])���� �����ؾ� �Ѵ�.
		students = (T[]) (new Object[capacity]);	
		// ��� Ŭ������  �θ��� Obj�� �����ؼ� T�� �ٽ� ĳ���� ���ְڴٴ� �ǹ�.
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
