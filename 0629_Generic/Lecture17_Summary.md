# Generic
Key Word : 제네릭, 멀티 타입 파라미터, 제네릭 메소드, 제한된 타입 파라미터(extends), 와일드카드(?), 제네릭 타입의 상속과 구현

<hr/>

## 교재 659p : 13.3 멀티 타입 파라미터(class<K, V,... >, interface<K, V,...>)

 제네릭 타입은 두 개 이상의 멀티 타입 파라미터를 사용할 수 있는데, 이 경우 각 타입 파라미터를 콤마로 구분한다.	
	 
 -> 파라미터 타입의 양식처럼 지정해서 사용할 수 있다?!


**Product.java**
```java
public class Product<T, M> {
	private T kind;
	private M model;
	
	public T getKind() { return this.kind; }
	public M getModel() { return this.model; }
	
	public void setKind(T kind) { this.kind = kind; }
	public void setModel(M model) { this.model = model; }
}

```

**ProductExample.java**
```java
public class ProductExample {
	public static void main(String[] args) {
		Product<Tv, String> product1 = new Product<Tv, String>();
		product1.setKind(new Tv());
		product1.setModel("스마트Tv");
		Tv tv = product1.getKind();
		String tvModel = product1.getModel();
		System.out.println(tvModel);
		
		Product<Car, String> product2 = new Product<Car, String>();
		product2.setKind(new Car());
		product2.setModel("디젤");
		Car car = product2.getKind();
		String carModel = product2.getModel();
		System.out.println(carModel);
	}
}
```

 -> Product 타입으로 만들어서 Car와 Tv를 private하게 저장해둠.


<br/><br/>
<hr/>
  
## 교재 660p : 13.4 제네릭 메소드(<T, R> R method(T d))	  
   
 제네릭 메소드는 매개 타입과 리턴 타입으로 파라미터를 갖는 메소드를 말한다. 제네릭 메소드를 선언하는 방법은 리턴 타입 앞에 <>기호를 추가하고 타입 파라미터를 기술한 다음, 리턴 타입과 매개 타입으로 타입 파라미터를 사용하면 된다.	   
   
```
public <타입 파라미터,...> 리턴타입 메소드명(매개변수,...){...}
```
   
 제네릭 메소드는 두가지 방식으로 호출할 수 있다. 코드에서 타입 파라미터의 구체적인 타입을 명시적으로 지정해도 되고, 컴파일러가 매개값의 타입을 보고 구체적인 타입을 추정하도록 할 수 도 있다.   
   
```
리턴타입 변수 = <구체적인 타입> 메소드명(매개 값);
리턴타입 변수 = 메소드명(매개 값);
```
   
 다음 코드는 boxing() 메소드를 호출하는 코드이다.   
   
```java
Box<Integer> box = <Interger>boxing(100);		// 타입 파라미터를 명시적으로 Integer로 지정
Box<Integer> box = boxing(100);					// 타입 파라미터를 Integer로 추정
```

 -> boxing의 매개값 `100`이 int이므로 타입 파라미터를 Integer로 추정한다.	


<br/><br/>


#### 예제를 통한 이해  
  
**Util.java**  
```java
public class Util {
	public static <T> Box<T> boxing(T t) {		// 스태틱하고 제네릭한 메소드를 정의함.
		Box<T> box = new Box<T>();
		box.set(t);
		return box;
	}
}
```
   
**BoxingMethodExample.java**   
```java
public class BoxingMethodExample {
	public static void main(String[] args) {
		Box<Integer> box1 = Util.<Integer>boxing(100);
		int intValue = box1.get();
		
		Box<String> box2 = Util.boxing("홍길동");
		String strValue = box2.get();
	}
}
```
   
 스태틱하게 정의한 boxing 메소드를 Example에서 사용할 때 따로 객체화 하지 않고 사용할 수 있다. T는 Box의 파라미터이자 boxing의 공통적인 파라미터이다. Box라는 클래스와 `Util.boxing`의 매개변수를 하나로 묶어 사용하고 싶을 때 같은 매개변수 `<T>`로 묶어 정의하고 사용한다. 위의 설명처럼 그렇게 되면 제네릭 타입의 객체에 메소드를 저장할 때 메소드의 파라미터를 보고 알아서 추정해준다.    


**제네릭 타입 변수 저장**   

```
int value = Util.boxing(100);	// 제네릭 타입은 객체(Wrapper 타입)에 담아야한다.    
```


-> 박스가 제네릭하고 getter, setter가 정의되어 있는 클래스이다.   

**Box.java**
```java
public class Box<T> {
	private T t;
	public T get() { return t; }
	public void set(T t) { this.t = t; }
}
```

#### 제네릭 클래스와 제네릭 메소드를 이용한 Compare    

 Pair<K,V>로 선언 했으면 compare라는 메소드를 정의 할 때 사용하는 파라미터가 Pair<K,V>이므로 compare 제네릭 메소드에서도 같은 <K,V> 타입 파라미터를 가져야 한다.	

**Util.java 제네릭 메소드**
```java
 public class Util {
	public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
		boolean keyCompare = p1.getKey().equals(p2.getKey()) ;
		boolean valueCompare = p1.getValue().equals(p2.getValue());
	    return keyCompare && valueCompare;
	}
}
```

**Pair.java 제네릭 타입 클래스**
```java
 public class Pair<K, V> {
	private K key;
	private V value;

	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public void setKey(K key) { this.key = key; }
	public void setValue(V value) { this.value = value; }
	public K getKey()   { return key; }
	public V getValue() { return value; }
}

```
	

**CompareMethodExample.java**
```java
public class CompareMethodExample {
	public static void main(String[] args) {
		Pair<Integer, String> p1 = new Pair<Integer, String>(1, "사과");
		Pair<Integer, String> p2 = new Pair<Integer, String>(1, "사과");
		boolean result1 = Util.<Integer, String>compare(p1, p2);		// 구체적 타입 명시.
		if(result1) {
			System.out.println("논리적으로 동등한 객체입니다.");
		} else {
			System.out.println("논리적으로 동등하지 않는 객체입니다.");
		}
		
		Pair<String, String> p3 = new Pair<String, String>("user1", "홍길동");
		Pair<String, String> p4 = new Pair<String, String>("user2", "홍길동");
		boolean result2 = Util.compare(p3, p4);							// 타입 추정.
		if(result2) {
			System.out.println("논리적으로 동등한 객체입니다.");
		} else {
			System.out.println("논리적으로 동등하지 않는 객체입니다.");
		}
	}
}
```
	



<br/><br/>
<hr/>
  
## 교재 664p : 13.5 제한된 타입 파라미터(<T extends 최상위타입>)	  
   
 타입 파라미터에 지정되는 구체적인 타입을 제한할 필요가 종종 있다. 예를 들어 숫자를 연산하는 제네릭 메소드는 매개값으로 Number 타입 또는 하위 클래스 타입(Byte, Short, Double, Long, Integer)의 인스턴스만 가져와야 한다. 이것이 제한된 타입 파라미터가 필요한 이유이다. 제한된 타입 파라미터를 선언하려면 타입 파라미터 뒤에 extends 키워드를 붙이고 상위 타입을 명시하면 된다. 상위 타입은 클래스뿐만 아니라 인터페이스도 가능하다. 인터페이스라고 해서 implements를 사용하지 않는다.	
 
```java
public <T extends 상위타입> 리턴타입 메소드(매개변수,...){...}
```

 타입 파라미터에 지정되는 구체적인 타입은 상위 타입이거나 상위 타입의 하위 또는 구현 클래스만 가능하다. 주의할 점은 메소드의 중괄호 {} 안에서 타입 파라미터 변수로 사용 가능 한 것은 상위 타입의 맴버(필드, 메소드)로 제한된다. 하위 타입에만 있는 필드와 메소드는 사용할 수 없다.**(따로 하위에서 만든 것은 사용할 수 없다는 뜻인듯 하다.)** 아래 코드는 숫자 타입만 구체적인 타입으로 갖는 제네릭 메소드 `compare()` 이다. 두 개의 숫자 타입을 매개 값으로 받아 차이를 리턴한다.	
	
```java
public static <T extends Number> int compare(T t1, T t2){

   double v1 = t1.doubleValue();
   double v2 = t2.doubleValue();

   return Double.compare(v1,v2);
}
```

 `doubleValue()` 메소드는 Number 클래스에 정의되어 있는 메소드로 숫자를 double 타입으로 변환한다. `Double.compare()` 메소드는 첫 번째 매개값이 작으면 -1을, 같으면 0을, 크면 1을 리턴합니다.	
 	
 `Number` 안의 하위 타입들만 사용가능하다.

<br/><br/>

#### 제한된 타입 파라미터 

 `Number` 하위의 클래스만 사용할 수 있도록 `<T extends Number>` 제네릭 메소드를 만들었기 때문에 String은 사용 불가능 한 것을 확인할 수 있다.	
	
**Util.java**
```java
public class Util {
	public static <T extends Number> int compare(T t1, T t2) {
		double v1 = t1.doubleValue(); 
		//System.out.println(t1.getClass().getName());
		double v2 = t2.doubleValue();
		//System.out.println(t2.getClass().getName());
		return Double.compare(v1, v2);
	}
}
```

**BoundedTypeParameterExample.java**
```java
public class BoundedTypeParameterExample {
	public static void main(String[] args) {
		//String str = Util.compare("a", "b"); (x)
		// Number 하위 타입만 사용가능하기 때문에 String은 사용 불가능.
		
		// 뒤가 크면 -1 같으면 0 앞이크면 1
		int result1 = Util.compare(10, 20);
		System.out.println(result1);
		
		int result2 = Util.compare(4.5, 3);
		System.out.println(result2);
	}
}
```



<br/><br/>
<hr/>
  
## 교재 665p : 13.6 와일드카드 타입(`<?>, <? extends ...>, <? super ...>`)	  
   
 - `제네릭타입<?>` : Unbound WildCard(제한 없음)
	Unbound Wildcard는 `List<?>`와 같은 형태로 물음표만 가지고 정의되어 진다. 내부적으로는 **Object로 정의**되어서 사용되고 모든 타입을 인자로 받을 수 있다. 타입 파라미터에 의존하지 않는 메소드만을 사용하거나 Object 메소드에서 제공하는 기능으로 충분한 경우에 사용한다.

 - `제네릭타입<? extends 상위타입>` : Upper Bounded Wildcard(상위 클래스 제한)
	Upper bounded wildcard는 `List<? extends Foo>`와 같은 형태로 사용되고, **특정 클래스의 자식 클래스만**을 인자로 받겠다는 선언이다. 예시에서는 Foo 클래스를 상속받은 어떤 클래스가 와도 되지만 **사용할 수 있는 기능은 Foo클래스에 정의된 기능**만 사용가능 하다. 주로 변수의 제한을 완화하게 하기 위해서 사용된다

 - `제네릭타입<? super 하위타입>` : Lower Bounded Wildcard(하위 클래스 제한)
	Lower bounded wildcard는 `List<? super Foo>`와 같은 형태로 사용되고, Upper bounded와는 반대로 **특정 클래스의 부모클래스만**을 인자로 받겠다는 선언이다. 예시에서는 Foo클래스의 부모인 어떤 객체도 인자로 올 수 있지만, **사용할때는 Object로 취급**된다.

#### 와일드 카드 예제

 제네릭 타입 Course는 과정 클래스로 `과정 이름`과 `수강생`을 저장할 수 있는 배열을 가지고 있다. 타입 파라미터 T가 적용된 곳은 수강생 타입 부분이다.		
 

**Course.java**
```java
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
```
	
<br/>
	
 수강생이 될 수 있는 타입은 다음 4가지 클래스라고 가정하자. Person의 하위 클래스로 Worker와 Student가 있고, Student의 하위 클래스로 HighStudent가 있다.		
	
![image](https://user-images.githubusercontent.com/84966961/123728241-f11c6f80-d8cd-11eb-8b4b-9d6d6ce59937.png)		
	
```
 - Course<?>
	수강생은 모든 타입(Person, Worker, Student, HighStudent)이 될 수 있다.
 - Course<? extends Student>
	수강생은 Student와 HighStudent만 될 수 있다. 
 - Course<?>
	수강생은 Person과 Worker만 될 수 있다.
```
	
 다음 예제는 `registerCourseXXX ()` 메소드의 매개값으로 와일드카드 타입을 사용하였다. `registerCourse ()`는 모든 수강생이 들을 수 있는 과정을 등록하고, `registerCourseStudetn ()`는 학생만 들을 수 있는 과정을 등록한다. 그리고 registerCouseWorker는 직장인만 들을 수 있는 과정을 등록한다.		
	

**WildCardExample.java**
```java
public class WildCardExample {
	public static void registerCourse(Course<?> course) {			// 모든과정
		System.out.println(course.getName() + " 수강생: " + 
				Arrays.toString(course.getStudents()));
 	}
	
	public static void registerCourseStudent(Course<? extends Student> course) {		// 학생 과정
		System.out.println(course.getName() + " 수강생: " + 
				Arrays.toString(course.getStudents()) );
 	}
	
	public static void registerCourseWorker(Course<? super Worker> course) {		// 직장인과 일반인 과정
		System.out.println(course.getName() + " 수강생: " + 
				Arrays.toString(course.getStudents()));
	}
	
	public static void main(String[] args) {
		Course<Person> personCourse = new Course<Person>("일반인과정", 5);		// 5는 Course의 Capacity 배열 크기 설정
			personCourse.add(new Person("일반인"));
			personCourse.add(new Worker("직장인"));
			personCourse.add(new Student("학생"));
			personCourse.add(new HighStudent("고등학생"));
			
		Course<Worker> workerCourse = new Course<Worker>("직장인과정", 5);
			workerCourse.add(new Worker("직장인"));
			
		Course<Student> studentCourse = new Course<Student>("학생과정", 5);
			studentCourse.add(new Student("학생"));
			studentCourse.add(new HighStudent("고등학생"));
			
		Course<HighStudent> highStudentCourse = new Course<HighStudent>("고등학생과정", 5);
			highStudentCourse.add(new HighStudent("고등학생"));	
		
			// 모든 과정 등록 가능
		registerCourse(personCourse);
		registerCourse(workerCourse);
		registerCourse(studentCourse);
		registerCourse(highStudentCourse);
		System.out.println();
		
			// 학생 과정만 등록 가능
		//registerCourseStudent(personCourse); 			(x)
		//registerCourseStudent(workerCourse); 			(x)
		registerCourseStudent(studentCourse);
		registerCourseStudent(highStudentCourse);	
		System.out.println();
		
			// 직장인과 일반인 과정만 등록 가능
		registerCourseWorker(personCourse);
		registerCourseWorker(workerCourse);
		//registerCourseWorker(studentCourse); 			(x)
		//registerCourseWorker(highStudentCourse); 		(x)
	}
}
```

 -> **제네릭 메소드 매개 변수에 와일드 카드 기입 방식에 따라 메소드 매개변수에 들어갈 수 있는 인자가 정해진다.**
	




<br/><br/>
<hr/>
  
## 교재 669p : 13.7 제네릭 타입의 상속과 구현		  
   
  제네릭 타입도 다른 타입과 마찬가지로 부모 클래스가 될 수 있다. 다음은 Product<T, M> 제네릭 타입을 상속해서 ChildProduct<T, M> 타입을 정의한다.	

```java
public class ChildProduct<T, M> extends Product<T, M> { ... }
```

 자식 제네릭 타입은 추가적으로 타입 파라미터를 가질 수 있다. 다음은 **세 가지 타입 파라미터를 가진 자식 제네릭 타입**을 선언한 것이다.

```java
public class ChildProduct<T, M, C> extends Product<T, M> { ... }
```

<br/><br/>

#### 제네릭 클래스의 상속 예제

**Product.java 부모 제네릭 클래스**
```
public class Product<T, M> {
	private T kind;
	private M model;
	
	public T getKind() { return this.kind; }
	public M getModel() { return this.model; }
	
	public void setKind(T kind) { this.kind = kind; }
	public void setModel(M model) { this.model = model; }
}

class Tv {}
```

**ChildProduct.java 자식 제네릭 클래스**
```
public class ChildProduct<T, M, C> extends Product<T, M> {
	   private C company;
	   public C getCompany() { return this.company; }
	   public void setCompany(C company) { this.company = company; }
}
```


<br/><br/>

#### 제네릭 인터페이스의 상속 예제   

**Storage.java 제네릭 인터페이스**
```
public interface Storage<T> {
	public void add(T item, int index);
	public T get(int index);
}
```
   
 **제네릭 인터페이스를 구현할 때는 구현하는 클래스 또한 제네릭 타입이어야 한다.**   
   
**StorageImpl.java 제네릭 구현 클래스**
```
public class StorageImpl<T> implements Storage<T> {
	private T[] array;
	
	public StorageImpl(int capacity) {
		this.array = (T[]) (new Object[capacity]);
	}
	
	@Override
	public void add(T item, int index) {
		array[index] = item;
	}

	@Override
	public T get(int index) {
		return array[index];
	}
}
```
   
 다음 ChildProductAndStorageExample은 `ChildProduct<T, M, C>`와 `StorageImpl<T>` 클래스의 사용 방법을 보여준다.   
   
**ChildProductAndStorageExample.java 제네릭 타입 사용 클래스**
```
public class ChildProductAndStorageExample {
	public static void main(String[] args) {
		ChildProduct<Tv, String, String> product = new ChildProduct<>();
		product.setKind(new Tv());
		product.setModel("SmartTV");
		product.setCompany("Samsung");
		
		Storage<Tv> storage = new StorageImpl<Tv>(100);
		storage.add(new Tv(), 0);
		Tv tv = storage.get(0);
	}
}

```
  

-> 상속되어 만들어진 ChildProduct의 회사명을 `System.out.println(product.getCompany());`로 받아볼 수 있다. 	
	
-> Tv 라는 배열로 storage 내부에 만들어졌다. storage 내부의 Tv 배열 0 번째에 새로운 Tv()를 만들어 넣었다. tv에는 Tv배열 안의 Tv가 들어있다.



<br/><br/>
<hr/>
