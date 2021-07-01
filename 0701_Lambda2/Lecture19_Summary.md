# Lecture19 : 람다 (Lambda)
Key Word : 람다식, 클래스 멤버와 로컬 변수 사용, 로컬 변수 사용,

<hr/>


 ## 교재 685p : 14.4 클래스 멤버와 로컬 변수 사용   
     
 람다식의 실행 블록에는 클래스의 멤버 (필드와 메소드) 및 로컬 변수를 사용할 수 있다. 클래스의 멤버는 제약 사항 없이 사용 가능하지만, 로컬 변수는 제약 사항이 따른다. 자세한 내용을 알아보기로 하자.   
   

<br/>
---
 
 ### 교재 685p : 14.4.1 클래스의 멤버 사용   
       
 람다식 실행 블록에는 클래스의 멤버인 필드와 메소드를 제약 사항 없이 사용할 수 있다. 하지만 this 키워드를 사용할 때에는 주의가 필요하다. 일반적으로 익명 객체 내부에서 this는 익명 객체의 참조이지만, 람다식에서 this는 내부적으로 생성되는 익명 객체의 참조가 아니라 람다식을 실행한 객체의 참조이다. 다음 예제는 람다식에서 바깥 객체와 중첩 객체의 참조를 얻어 필드값을 출력하는 방법을 보여주고 있다. 중첩 객체 Inner에서 람다식을 실행했기 때문에 람다식 내부에서는 this는 중첩 객체 Inner이다.   
    
**UsingThis.java**
```java
public class UsingThis {
	public int outterField = 10;

	class Inner {
		int innerField = 20;

		void method() {
			int innerField = 0;
			//람다식
			MyFunctionalInterface fi= () -> {
				System.out.println("outterField: " + outterField);
				System.out.println("outterField: " + UsingThis.this.outterField + "\n");
				
				System.out.println("innerField: " + innerField);
				System.out.println("innerField: " + this.innerField + "\n");
			};
			fi.method();
		}
	}
}
```

**UsingThisExample.java**
```java
public class UsingThisExample {
	public static void main(String... args) {
		UsingThis usingThis = new UsingThis();			// 외부 클래스 인스턴스 생성
		UsingThis.Inner inner = usingThis.new Inner();	// 중첩 클래스 인스턴스 생성
		inner.method();
	}
}
```
   
**결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/124049824-45e9f280-da54-11eb-8e69-356c160e39f1.png)


<br/><br/>
---
 
 ### 교재 687p : 14.4.2 로컬 변수 사용   
   
 람다식은 메소드 내부에서 주로 작성되기 때문에 로컬 익명 구현 객체를 생성시킨다고 봐야 한다. 람다식에서 바깥 클래스의 필드나 메소드는 제한 없이 사용할 수 있으나, 메소드의 매개 변수 또는 로컬 변수를 사용하면 이 두 변수는 Final 특성을 가져야 한다. 왜 final 특성을 가져야 되는지 이유를 알고 싶다면 9.5.3 익명객체의 로컬 변수 사용을 읽어보길 바란다. 따라서 매개 변수 또는 로컬 변수를 람다식에서 읽는 것은 허용되지만, 람다식 내부 또는 외부에서 변경할 수 없다. 다음 예제 코드를 보면서 이해해보자.   
   
   
**UsingLocalVariable.java**
```java
public class UsingLocalVariable {
	void method(int arg) {  //arg는 final 특성을 가짐
		int localVar = 40; 	//localVar는 final 특성을 가짐
		
		//arg = 31;  		//final 특성 때문에 수정 불가
		//localVar = 41; 	//final 특성 때문에 수정 불가
        
		//람다식
		MyFunctionalInterface fi= () -> {
			//로컬변수 사용
			System.out.println("arg: " + arg); 
			System.out.println("localVar: " + localVar + "\n");
		};
		fi.method();
	}
}
```

**UsingLocalVariableExample.java**
```java
public class UsingLocalVariableExample {
	public static void main(String... args) {
		UsingLocalVariable ulv = new UsingLocalVariable();
		ulv.method(20);
	}
}
```

**결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/124049853-5306e180-da54-11eb-8560-9ea46b9a792d.png)





<br/><br/>
<hr/>

 ## 교재 688p : 14.5 표준 API의 함수적 인터페이스    
   
 자바에서 제공되는 표준 API에서 한 개의 추상 메소드를 가지는 인터페이스 들은 모두 람다식을 이용해서 익명 구현 객체로 표현이 가능하다. 예를 들어 스레드의 작업을 정의하는 Runnable 인터페이스는 매개 변수와 리턴값이 없는 run() 메소드만 존재하기 때문에 다음과 같이 람다식을 이용해서 Runnable 인스턴스를 생성시킬 수 있다.   
   
****
```java

```
   
 자바 8부터는 빈번하게 사용되는 함수적 인터페이스(functional interface)는 java.util.function 표준 API 패키지로 제공한다. 이 패키지에서 제공하는 **함수적 인터페이스의 목적**은 **메소드 또는 생성자의 매개 타입으로 사용**되어 람다식을 대입할 수 있도록 하기 위해서이다. 자바 8 부터 추가되거나 변경된 API에서 이 함수적 인터페이스들을 매개 타입으로 많이 사용한다. 물론 여러분이 개발하는 메소드에도 이 함수적 인터페이스들을 매개 타입으로 사용할 수 있다. java.util.function 패키지의 함수적 인터페이스는 다음 표와 같다. 구분 기준은 인터페이스에 선언된 추상 메소드의 매개값과 리턴값의 유무이다.

| 종류 | 추상 메소드 특징 |   |
|---------|----------|---------|
| Consumer | - 매개값은 있고, 리턴값은 없음 | ![image](https://user-images.githubusercontent.com/84966961/124051115-d295b000-da56-11eb-9719-df201b6eb39d.png) |
| Supplier | - 매개값은 없고, 리턴값은 있음 | ![image](https://user-images.githubusercontent.com/84966961/124051502-9282fd00-da57-11eb-876e-d110be2ff3ab.png) |
| Function | - 매개값도 있고, 리턴값도 있음<br> - 주로 매개값을 리턴값으로 매핑(타입변환) | ![image](https://user-images.githubusercontent.com/84966961/124051490-8f880c80-da57-11eb-812a-3ab2e8c37dc4.png) |
| Operator | - 매개값도 있고, 리턴값도 있음<br> - 주로 매개값을 연산하고 결과를 리턴 | ![image](https://user-images.githubusercontent.com/84966961/124051516-99aa0b00-da57-11eb-897c-d9c51b458e9b.png) |
| Predicate | - 매개값도 있고, 리턴값은 boolean<br> - 매개값을 조사해서 true/false를 리턴 | ![image](https://user-images.githubusercontent.com/84966961/124051524-9d3d9200-da57-11eb-938b-d51acd1dc484.png) |




<br/><br/>
---

 ### 교재 689p : 14.5.1 Consumer 함수적 인터페이스    
   
 Consumer 함수적 인터페이스의 특징은 리턴값이 없는 accept() 메소드를 가지고 잇다. accept () 메소드는 단지 매개값을 소비하는 역할만 한다. 여기서 소비한다는 말은 사용만 할 뿐 리턴값이 없다는 뜻이다.   
   
<img src="https://user-images.githubusercontent.com/84966961/124051115-d295b000-da56-11eb-9719-df201b6eb39d.png" width="40%">   
   
 매개 변수의 타입과 수에 따라서 아래와 같은 Consumer들이 있다.     
   
 인터페이스명 | 추상 메소드 | 설명
--------|--------|---
Consumer | void accept(T t) | 객체 T를 받아 소비
BiConsumer<T, U> | void accept(T t, U u) | 객체 T와 U를 받아 소비
DoubleConsumer | void accept(double value) | double 값을 받아 소비
IntConsumer | void accept(int value) | int값을 받아 소비
LongConsumer | void accept(long value) | long 값을 받아 소비
ObjDoubleConsumer | void accept(T t, double value) | 객체 T와 double 값을 받아 소비
ObjIntConsumer | void accept(T t, int value) | 객체 T와 int 값을 받아 소비
ObjLongConsumer | void accept(T t, long value) | 객체 T와 long 값을 받아 소비
    
 `Consumer<T>` 인터페이스를 타겟 타입으로 하는 람다식은 다음과같이 작성할 수 있다. accept() 메소드는 매개값으로 T 객체 하나를 가지므로 람다식도 한 개의 매개 변수를 사용한다. 타입 파라미터 T에 String이 대입되었기 때문에 람다식의 t 매개 변수 타입은 String이 된다.   

```java
 Consumer<String> consumer = t -> { t를 소비하는 실행문 };
```
   

#### 예제
   
**ConsumerExample.java**
```java
public class ConsumerExample {
	public static void main(String[] args) {
		Consumer<String> consumer = t -> System.out.println(t + "8");
		consumer.accept("java");
		
		BiConsumer<String, String> bigConsumer = (t, u) -> System.out.println(t + u);
		bigConsumer.accept("Java", "8");
		
		DoubleConsumer doubleConsumer = d -> System.out.println("Java" + d);
		doubleConsumer.accept(8.0);
		
		ObjIntConsumer<String> objIntConsumer = (t, i) -> System.out.println(t + i);
		objIntConsumer.accept("Java", 8);
	}
}

```



<br/><br/>
---

 ### 교재 692p : 14.5.2 Supplier 함수적 인터페이스    
   
 Supplier 함수적 인터페이스의 특징은 매개 변수가 없고 리턴값이 있는 getXXX() 메소드를 가지고 있다. 이 메소드들은 실행 후 호출한 곳으로 데이터를 리턴(공급)하는 역할을 한다.   
   
<img src="https://user-images.githubusercontent.com/84966961/124051502-9282fd00-da57-11eb-876e-d110be2ff3ab.png" width="40%">   
   
리턴 타입에 따라서 아래와 같은 Supplier 함수적 인터페이스들이 있다.

인터페이스명 | 추상 메소드 | 설명
-------|--------|---
Supplier | T.get() | T 객체를 리턴
BooleanSupplier | boolean getAsBoolean() | boolean 값을 리턴
DoubleSupplier | double getAsDouble() | double 값을 리턴
IntSupplier | int getAsInt() | int 값을 리턴
LongSupplier | long getAsLong() | long 값을 리턴



#### 예제
   
**SupplierExample.java**
```java
public class SupplierExample {
	public static void main(String[] args) {
		IntSupplier intSupplier = () -> {
			int num = (int) (Math.random() * 6) + 1;
			return num;
		};
		
		int num = intSupplier.getAsInt();			// 1 ~ 6 랜덤 리턴
		System.out.println("눈의 수: " + num);		
	}
}
```







<br/><br/>
---

 ### 교재 693p : 14.5.3 Function 함수적 인터페이스    
   
 Function 함수적 인터페이스의 특징은 매개값과 리턴값이 있는 applyXXX() 메소드를 가지고 있다. 이 메소드 들은 매개값을 리턴값으로 매핑(타입 변환)하는 역할을 한다.   
    
<img src="https://user-images.githubusercontent.com/84966961/124051490-8f880c80-da57-11eb-812a-3ab2e8c37dc4.png" width="40%">   
   
 매개 변수 타입과 리턴 타입에 따라서 아래와 같은 Function 함수적 인터페이스들이 있다.
   
인터페이스명 | 추상메서드 | 설명
-------|-------|---
Function<T,R> | R apply(T t) | 객체 T를 객체 R로 매핑
BiFunction<T,U,R> | R apply(T t, U u) | 객체 T와 U를 객체 R로 매핑
DoubleFunction | R apply(double val) | double 를 객체 R로 매핑
IntFunction | R apply(int val) | int 를 객체 R로 매핑
IntToDoubleFunction | double applyAsdouble(int val) | int를 double로 매핑
IntToLongFunction | long applyAsLong(int val) | int를 long로 매핑
LongToDoubleFunction | double applyAsdouble(long val) | long을 double로 매핑
LongToIntFunction | int applyAsInt(long val) | long을 int로 매핑
ToDoubleBiFunction<T,U> | double applyAsDouble(T t, U u) | 객체 T와 U를 double 로 매핑
ToDoubleFunction | double applyAsdouble(T t) | 객체 T를 double로 매핑
ToIntBiFunction<T,U> | int applyAsInt(T t, U u) | 객체 T와 U를 int로 매핑
ToIntFunction | int applyAsInt(T t) | 객체 T를 int로 매핑
ToLongBiFunction<T,U> | long applyAsLong(T t, U u) | 객체 T와 U를 long으로 매핑
ToLongFunction | long applyAsLong(T t) | 객체 T를 long으로 매핑

**ArrayList**   
 최초 생성시 10개이며 부족할 경우 늘어남. 하지만 이 예제에서는 크기를 고정하여 사용하고 내용을 추가하여 사용할 수 없도록 ArrayList를 사용하는 식이다.   
   
 `Function<T,R>` 인터페이스를 타겟 타입으로 하는 람다식은 다음과 같이 작성할 수 있다. apply() 메소드는 매개값으로 T 객체 하나를 가지므로 람다식도 한 개의 매개 변수를 사용한다. 그리고 apply() 메소드의 리턴타입이 R이므로 람다식 중괄호 {}의 리턴값은 R 객체가 된다. T가 Student 타입이고 R이 String 타입이므로 t 매개 변수 타입은 Student가 되고, 람다식의 중괄호 {}는 String을 리턴해야 한다. t.getName()은 Student 객체의 getName()메소드를 호출해서 학생 이름(String)을 얻는다. return문만 있을 경우 중괄호 {}와 return문은 생략할 수 있다는 것을 이미 배웟다. 다음 코드는 Student 객체를 학생 이름(String)으로 매핑하는 것이다.    
  
```java
Function<Student, String> function = t -> { return t.getName(); };
또는
Function<Student, String> function = t -> t.getName();
```
   
 `ToIntFunction<T>` 인터페이스를 타겟 타입으로 하는 람다식은 다음과 같이 작성할 수 있다. applyAsInt() 메소드는 매개값으로 T 객체 하나를 가지므로 람다식도 한 개의 매개 변수를 사용한다. 그리고 applyAsInt() 메소드의 리턴타입이 int 이므로 람다식 중괄호 {}의 리턴값은 int가 된다. T가 Student 타입이므로 t 매개 변수 타입은 Student가 된다. t.getScore()는 Student 객체의 getScore() 메소드를 호출해서 학생 점수 (int)를 얻는다. 다음 코드는 Student 객체를 학생 점수(int)로 매핑하는 것이라고 볼 수 있다.   
 

```java
ToIntFunction<Student> function = t -> {return t.getScore();}
또는
ToIntFunction<Student> function = t -> t.getScore();
```

#### 예제

 다음 예제는 List에 저장된 학생 객체를 하나씩 꺼내서 이름과 점수를 출력한다. FunctionExample1의 `ToIntFunction<Student>` 매개 변수를 가지고 있으므로 이 메소드들을 호출할 때 매개값으로 람다식을 사용할 수 있다.   

 람다식이 등장하면서 함수 자체를 매개 변수 값으로 사용할 수 있게 되었다. (Function은 함수에서 리턴값이 나오므로 그것을 매개변수로 사용한다.)
`public static void printString(Function<Student, String> function)` 단순히 Student를 String으로 매핑(타입 변환)을 해주는 역할이다.



**FunctionExample1.java**
```java
package function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class FunctionExample1 {
	private static List<Student> list = Arrays.asList(
		new Student("홍길동", 90, 96),
		new Student("신용권", 95, 93)
	);
	
	public static void printString(Function<Student, String> function) { // 매
		for(Student student : list) {
			System.out.print(function.apply(student) + " ");
		}
		System.out.println();
	}
	
	public static void printInt(ToIntFunction<Student> function) {
		for(Student student : list) {
			System.out.print(function.applyAsInt(student) + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		System.out.println("[학생 이름]");
		printString( t -> t.getName() );
		
		System.out.println("[영어 점수]");
		printInt( t -> t.getEnglishScore() );
		
		System.out.println("[수학 점수]");
		printInt( t -> t.getMathScore() );
	}
}
```

다음 예제는 List에 저장된 학생 객체를 하나씩 꺼내어 영어 점수와 수학 점수의 평균값을 산출한다. 

 향상된 for문을 이용해서 해당 점수의 평균을 낸다.

**FunctionExample2.java**
```java
public class FunctionExample2 {
	private static List<Student> list = Arrays.asList(
		new Student("홍길동", 90, 96),
		new Student("신용권", 95, 93)
	);
	
	private static List<Student> list2 = Arrays.asList(
			new Student("조영민", 100, 97),
			new Student("음동원", 95, 93)
		);
		
	public static double avg(List<Student> list, ToIntFunction<Student> function) {		// 향상된 포문을 돌면서 총 점수 구함.
		int sum = 0;
		for(Student student : list) {
			sum += function.applyAsInt(student);
		}
		double avg = (double) sum / list.size();
		return avg;
	}
	
	public static void main(String[] args) {
		double englishAvg = avg(list, s -> s.getEnglishScore() );
		System.out.println("1반 영어 평균 점수: " + englishAvg);
		
		double englishAvg2 = avg(list2, s -> s.getEnglishScore() );
		System.out.println("2반 영어 평균 점수: " + englishAvg2);
		
		double mathAvg = avg(list, s -> s.getMathScore() );
		System.out.println("수학 평균 점수: " + mathAvg);
	}
}
```

#### 반의 학생별 평균 점수 구하기
   
 반의 학생별 평균 점수도 구해보고 싶어서 다음과 같이 연습삼아 작성해보았다.   
   
```java
public class FunctionExample2 {
	private static List<Student> list = Arrays.asList(
		new Student("홍길동", 90, 96),
		new Student("신용권", 95, 93)
	);
	
	private static List<Student> list2 = Arrays.asList(
			new Student("조영민", 100, 97),
			new Student("음동원", 95, 93)
		);
		
	public static double avg(List<Student> list, ToIntFunction<Student> function) {		// 향상된 포문을 돌면서 총 점수 구함.
		int sum = 0;
		for(Student student : list) {
			sum += function.applyAsInt(student);
		}
		double avg = (double) sum / list.size();
		return avg;
	}
	
	public static void groupAvg(List<Student> list) {
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			Student s = list.get(i);
			int avg = (s.getEnglishScore() + s.getMathScore())/2;
			System.out.println((i+1) + "번 학생 " +s.getName()+"의 평균 점수 : " + avg);
		}
		
	}
	
	public static void main(String[] args) {
		double englishAvg = avg(list, s -> s.getEnglishScore() );
		System.out.println("1반 영어 평균 점수: " + englishAvg);
		
		double englishAvg2 = avg(list2, s -> s.getEnglishScore() );
		System.out.println("2반 영어 평균 점수: " + englishAvg2);
		
		double mathAvg = avg(list, s -> s.getMathScore() );
		System.out.println("수학 평균 점수: " + mathAvg);
		
		System.out.println("");
		System.out.println("1반 학생별 평균");
		groupAvg(list);
	}
}
```


<br/><br/>
---

 ### 교재 697p : 14.5.4 Operator 함수적 인터페이스    
   
 Operator 함수적 인터페이스는 Function과 동일하게 매개 변수와 리턴값이 있는 applyXXX() 메소드를 가지고 있다. 하지만 이 메소드들은 매개값을 리턴값으로 매핑(타입 변환)하는 역할보다는 매개값을 이용해서 연산을 수행한 후 동일한 타입으로 리턴값을 제공하는 역할을 한다.   
   

   
매개 변수의 타입과 수에 아래와 같은 Operator 함수적 인터페이스들이 있다.

인터페이스명 | 추상메서드 | 설명
-------|-------|---
BinaryOperator | BiFunction<T,U,R>의 하위 인터페이스 | T와 U를 연산한 후 R 리턴
UnaryOperator | Function<T,R>의 하위 인터페이스 | T를 연산한 후 R 리턴
DoubleBinaryOperator | double applyAsDouble(double, double) | 두 개의 double 연산
DoubleUnaryOperator | double applyAsDouble(double) | 한 개의 double 연산
IntBinaryOperator | int applyAsInt(int,int) | 두 개의 int 연산
IntUnaryOperator | int applyAsInt(int) | 한 개의 int 연산
LongBinaryOperator | long applyAsLong(long, long) | 두 개의 long 연산
LongUnaryOperator | long applyAsLong(long) | 한 개의 long 연산







인터페이스명 | 추상 메소드 | 설명
-------|--------|---
Predicate | boolean test(T t) | 객체 T를 조사
BiPredicate<T, U> | boolean test(T t, U u) | 객체 T와 U를 비교 조사
DoublePredicate | boolean test(double value) | double 값을 조사
IntPredicate | boolean test(int value) | int 값을 조사
LongPredicate | boolean test(long value) | long 값을 조사



종류 | 함수적 인터페이스 | andThen() | compose()
---|-----------|-----------|----------
Consumer | Consumer | O | 
BiConsumer<T, U> | O | 
DoubleConsumer | O | 
IntConsumer | O | 
LongConsumer | O | 
Function | Function<T, R> | O | O
BiFunction<T, U, R> | O | 
Operator | BinaryOperator | O | 
DoubleUnaryOperator | O | O
IntUnaryOperator | O | O
LongUnaryOperator | O | O





















