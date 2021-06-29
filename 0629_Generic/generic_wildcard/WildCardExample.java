package generic_wildcard;

import java.util.Arrays;

public class WildCardExample {
	public static void registerCourse(Course<?> course) {			// ������
		System.out.println(course.getName() + " ������: " + 
				Arrays.toString(course.getStudents()));
 	}
	
	public static void registerCourseStudent(Course<? extends Student> course) {		// �л� ����
		System.out.println(course.getName() + " ������: " + 
				Arrays.toString(course.getStudents()) );
 	}
	
	public static void registerCourseWorker(Course<? super Worker> course) {		// �����ΰ� �Ϲ��� ����
		System.out.println(course.getName() + " ������: " + 
				Arrays.toString(course.getStudents()));
	}
	
	public static void main(String[] args) {
		Course<Person> personCourse = new Course<Person>("�Ϲ��ΰ���", 5);		// 5�� Course�� Capacity �迭 ũ�� ����
			personCourse.add(new Person("�Ϲ���"));
			personCourse.add(new Worker("������"));
			personCourse.add(new Student("�л�"));
			personCourse.add(new HighStudent("����л�"));
			
		Course<Worker> workerCourse = new Course<Worker>("�����ΰ���", 5);
			workerCourse.add(new Worker("������"));
			
		Course<Student> studentCourse = new Course<Student>("�л�����", 5);
			studentCourse.add(new Student("�л�"));
			studentCourse.add(new HighStudent("����л�"));
			
		Course<HighStudent> highStudentCourse = new Course<HighStudent>("����л�����", 5);
			highStudentCourse.add(new HighStudent("����л�"));	
		
			// ��� ���� ��� ����
		registerCourse(personCourse);
		registerCourse(workerCourse);
		registerCourse(studentCourse);
		registerCourse(highStudentCourse);
		System.out.println();
		
			// �л� ������ ��� ����
		//registerCourseStudent(personCourse); 			(x)
		//registerCourseStudent(workerCourse); 			(x)
		registerCourseStudent(studentCourse);
		registerCourseStudent(highStudentCourse);	
		System.out.println();
		
			// �����ΰ� �Ϲ��� ������ ��� ����
		registerCourseWorker(personCourse);
		registerCourseWorker(workerCourse);
		//registerCourseWorker(studentCourse); 			(x)
		//registerCourseWorker(highStudentCourse); 		(x)
	}
}
