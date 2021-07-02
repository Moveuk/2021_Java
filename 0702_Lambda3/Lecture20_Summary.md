# Lecture20 : 람다 (Lambda)
Key Word : 람다식, 클래스 멤버와 로컬 변수 사용, 로컬 변수 사용, 

<hr/>

 ### 교재 697p : 14.5.4 Operator 함수적 인터페이스    

 Operator 함수적 인터페이스는 Function과 동일하게 매개 변수와 리턴값이 있는 applyXXX() 메소드를 가지고 있다. 하지만 이 메소드들은 매개값을 리턴값으로 매핑(타입 변환)하는 역할보다는 매개값을 이용해서 연산을 수행한 후 동일한 타입으로 리턴값을 제공하는 역할을 한다.   

<img src="https://user-images.githubusercontent.com/84966961/124051516-99aa0b00-da57-11eb-897c-d9c51b458e9b.png" width="40%">

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
   
 IntBinaryOperator 인터페이스를 타겟 타입으로 하는 람다식은 다음과 같이 작성할 수 있다. applyAsInt() 메소드는 매개값으로 두개의 int를 가지므로 람다식도 두 개의 int 매개 변수 a와 b를 사용한다. 그리고 applyAsInt() 메소드의 리턴 타입이 int이므로 람다식의 중괄호 {}의 리턴값은 int가 된다. 다음 코드는 두 개의 int를 연산해서 결과값으로 int를 리턴한다.   
   
```java
IntBinaryOperator operator = (a, ) -> { ...; return int값; }
```

<br/><br/>

#### 예제

****
```java
public class OperatorExample {
	private static int[] scores = { 92, 95, 87 };
	
	public static int maxOrMin(IntBinaryOperator operator) {
		int result = scores[0];
		for(int score : scores) {
			result = operator.applyAsInt(result, score);
		}
		return result;
	}
	
	public static void main(String[] args) {
		//최대값 얻기
		int max = maxOrMin(
				// 람다식이 11번째 줄 `operator.applyAsInt(result, score);`로 들어가게됨.
			(a, b) -> {
				if(a>=b) return a;
				else return b;
			}
		);
		System.out.println("최대값: " + max);
		
		//최소값 얻기
		int min = maxOrMin(
			(a, b) -> {
				if(a<=b) return a;
				else return b;
			}
		);
		System.out.println("최소값: " + min);
	}
}
```

<br/><br/>

#### 향상된 포문 이해하기
   
```
	public static int maxOrMin(IntBinaryOperator operator) {
		int result = scores[0];
		for(int score : scores) {
			result = operator.applyAsInt(result, score);
		}
		return result;
	}
```

 위의 포문을 풀어 이해해 보자면 `int score : scores` scores 배열의 0번째부터 끝까지 포문 내용 안의 score에 하나씩 넣어가며 반복하겠다는 뜻이다. 즉,   
 
 ```
 1 번째 : result = operator.applyAsInt(result, score[0]); 
 2 번째 : result = operator.applyAsInt(result, score[1]); 
 3 번째 : result = operator.applyAsInt(result, score[2]);
 ...
 ```
 
 이런식으로 나아갈 것이며 배열이 끝날 때까지 반복한다.


<br/><br/>
<hr/>

 ### 교재 699p : 14.5.5 Predicate 함수적 인터페이스    
   
 Predicate 함수적 인터페이스는 매개 변수와 boolean 리턴값이 있는 testXXX() 메소드를 가지고 있다. 이 메소드들은 매개값을 조사해서 true 또는 false를 리턴하는 역할을 한다.   
   
<img src="https://user-images.githubusercontent.com/84966961/124051516-99aa0b00-da57-11eb-897c-d9c51b458e9b.png" width="40%">   
   

매개 변수 타입과 수에 따라서 아래와 같은 Predicate 함수적 인터페이스들이 있다.
   
인터페이스명 | 추상 메소드 | 설명
-------|--------|-----
Predicate | boolean test(T t) | 객체 T를 조사
BiPredicate<T, U> | boolean test(T t, U u) | 객체 T와 U를 비교 조사
DoublePredicate | boolean test(double value) | double 값을 조사
IntPredicate | boolean test(int value) | int 값을 조사
LongPredicate | boolean test(long value) | long 값을 조사



 
 
<br/><br/>

#### 예제

 다음 예제는 List에 저장된 남자 또는 여자 학생들의 평균 점수를 출력한다. avg() 메소드는 `Predicate<Student>` 매개 변수를 가지고 있다. 따라서 avg() 메소드를 호출할 때 매개값으로 람다식을 사용할 수 있다.   
   
**PredicateExample.java**
```java
public class PredicateExample {
	private static List<Student> list = Arrays.asList(
			new Student("홍길동", "남자", 90),
			new Student("김순희", "여자", 90),
			new Student("감자바", "남자",  95),
			new Student("박한나", "여자", 92)
	);
		
	public static double avg(Predicate<Student> predicate) {
		int count = 0, sum = 0;
		for(Student student : list) {
			if(predicate.test(student)) {
				count++;
				sum += student.getScore();
			}
		}
		return (double) sum / count;
	}
		
	public static void main(String[] args) {
		double maleAvg = avg( t ->  t.getSex().equals("남자") );
		System.out.println("남자 평균 점수: " + maleAvg);
			
		double femaleAvg = avg( t ->  t.getSex().equals("여자") );
		System.out.println("여자 평균 점수: " + femaleAvg);
	}
}
```
	
**Student.java**
```java
public class Student {
	private String name;
	private String sex;
	private int score;
	
	public Student(String name, String sex, int score) {
		this.name = name;
		this.sex = sex;
		this.score = score;
	}

	public String getSex() { return sex; }
	public int getScore() { return score; }
}
```

**코드 흐름**
	
![image](https://user-images.githubusercontent.com/84966961/124205638-962c8780-db1c-11eb-91e4-392907b7f2d7.png)




<br/><br/>
<hr/>

 ### 교재 702p : 14.5.6 andThen()과 compose() 디폴트 메소드    
   
 디폴트 및 정적 메소드는 추상 메소드가 아니기 때문에 함수적 인터페이스에 선언되어도 여전히 함수적 인터페이스의 성질을 잃지 않는다. 여기서 함수적 인터페이스 성질이란 하나의 추상 메소드를 가지고 있고, 람다식으로 익명 구현 객체를 생상할 수 있는 것을 말한다. java.util.function 패키지의 함수적 인터페이스는 하나 이상의 디폴트 및 정적 메소드를 가지고 있다.   
   
 Consumer, Function, Operator 종류의 함수적 인터페이스는 andThen()과 compose() 디폴트 메소드를 가지고 있다. andThen()과 compose() 디폴트 메소드는 두 개의 함수적 인터페이스를 순차적으로 연결하고, 첫번째 처리 결과를 두번째 매개값으로 제공해서 최종 결과값을 얻을 때 사용한다. andThen()과 compose()의 차이점은 어떤 함수적 인터페이스부터 먼저 처리하느냐이다. 먼저 다음 코드를 보면서 andThen()부터 살펴보자.   
    
```java
 인터페이스AB = 인터페이스A.andThen(인터페이스B);
 최종결과 = 인터페이스AB.method();
```
 
 인터페이스AB의 method()를 호출하면 우선 인터페이스 A부터 처리하고 결과를 인터페이스 B의 매개값으로 제공한다. 인터페이스 B는 제공받는 매개값을 가지고 처리한 후 최종 결과를 리턴한다.   
     
	 
**andThen() 흐름 모식도**
 
 ![image](https://user-images.githubusercontent.com/84966961/124206455-61213480-db1e-11eb-8252-2eae26680395.png)

 
 <hr/>
 
  이번에는 compose()를 살펴보자. 인터페이스 AB의 method()를 호출하면 우선 인터페이스 B부터 처리하고 결과를 인터페이스A의 매개값으로 제공한다. 인터페이스 A는 제공받는 매개값을 가지고 처리한 후 최종 결과를 리턴한다.   
     
```java
 인터페이스AB = 인터페이스A.compose(인터페이스B);
 최종결과 = 인터페이스AB.method();
```
 
 **compose() 흐름 모식도**
 ![image](https://user-images.githubusercontent.com/84966961/124206744-e7d61180-db1e-11eb-8153-8b996d9bf91e.png)


 다음은 andThen()과 compose() 디폴트 메소드를 제공하는 java.util.function 패키지의 함수적 인터페이스 들이다.

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

 <br><br>
 
 #### 추가 공부 : andThen 이미지 처리 이해하기
 
 https://jaeryo2357.tistory.com/96
 
 


<br/><br/>
<hr/>

 ## 교재 718p : 확인문제 5,6,7 문제 
   
<br/>

### 확인문제 5번

**LambdaExample_5.java**
```java
package exercise;

import java.util.function.IntBinaryOperator;

public class LambdaExample_5 {
	private static int[] scores = { 10, 50, 3};
	
	public static int maxOrMin(IntBinaryOperator operator) {
		int result = scores[0];
		for (int score : scores) {
			result = operator.applyAsInt(result, score);
		}
		return result;
	}
	

	public static void main(String[] args) {
		//최대값 얻기
		int max = maxOrMin( (a,b) -> {
			if (a>=b) {
				return a;
			}
			return b;
		});
		System.out.println("최대값: "+max);
		
		// 최소값 얻기
		int min = maxOrMin( (a,b) -> {
			if (a<=b) {
				return a;
			}
			return b;
		});
		System.out.println("최소값: "+min);
		}
}
```

   
<br/><br/>

### 확인문제 6번


**LambdaExample_6.java**
```java
package exercise;

import java.util.function.ToIntFunction;

public class LambdaExample_6 {
	
	private static Student[] students = {
			new Student("홍길동", 90, 96),
			new Student("신용권", 95, 93)
	};
			
	// avg() 메소드 작성
	public static double avg(ToIntFunction<Student> function) {
		double sum = 0;
		for (Student student : students) {
			sum += function.applyAsInt(student);
		}
		return sum / students.length;
	}
	

	public static void main(String[] args) {
		// 매개 변수의 메소드 참조 형으로 바꾸어보았다. (교재 714p)		// 확인 문제 7번.
		double englichAvg = avg( Student :: getEnglishScore);
		System.out.println("영어 평균 점수 : "+englichAvg);
		double mathAvg = avg( s -> s.getMathScore());
		System.out.println("수학 평균 점수 : "+mathAvg);
	}

	public static class Student {
		private String name;
		private int englishScore;
		private int mathScore;
		
		public Student(String name, int englishScore, int mathScore) {
			this.name = name;
			this.englishScore = englishScore;
			this.mathScore = mathScore;
		}

		public String getName() { return name; }
		public int getEnglishScore() { return englishScore; }
		public int getMathScore() { return mathScore; }
	}
}
```





<br/><br/>
<hr/>

 ## 선생님 출제 문제 
   
   
 **max 함수 완성**   
    
 - 기능 : 4명 학생 점수 중에 제일 높은 점수 출력.   
 - 함수형 인터페이스를 매개변수로 가지는 함수를 작성할 것.   
 - main 함수의 max에는 람다식을 작성할 것.   

**exercise.java chrl**
```java
public class Exercise {
	private static List<Student> list = Arrays.asList(
			new Student("홍길동", "남자", 90),
			new Student("김순희", "여자", 90),
			new Student("감자바", "남자",  95),
			new Student("박한나", "여자", 92)
	);
	
	// max 함수 완성
	// 기능 : 4명 학생 점수 중에 제일 높은 점수 출력.
	// 함수형 인터페이스를 매개변수로 가지는 함수를 작성할 것.
	// main 함수의 max에는 람다식을 작성할 것.

	
	public static void main(String[] args) {
		int max = max()
	}

	public static class Student {
		private String name;
		private String sex;
		private int score;
		
		public Student(String name, String sex, int score) {
			this.name = name;
			this.sex = sex;
			this.score = score;
		}

		public String getSex() { return sex; }
		public int getScore() { return score; }
	}
	
}
```






<br/><br/>
<hr/>

#### 나의 답안
   

```java
package exercise;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntBinaryOperator;

public class Exercise {
	private static List<Student> list = Arrays.asList(
			new Student("홍길동", "남자", 90),
			new Student("김순희", "여자", 90),
			new Student("감자바", "남자",  95),
			new Student("박한나", "여자", 92)
	);
	
	// max 함수 완성
	// 기능 : 4명 학생 점수 중에 제일 높은 점수 출력.
	// 함수형 인터페이스를 매개변수로 가지는 함수를 작성할 것.
	// main 함수의 max에는 람다식을 작성할 것.
	public static int max(IntBinaryOperator operator) {
		int max = list.get(0).getScore();
		for (Student student : list) {
			max = operator.applyAsInt(max, student.getScore());
		}
		return max;
	}
	
	public static void main(String[] args) {
		int max = max((a,b) -> {
			if (a >= b) {
				return a;
			}
			return b;
		});
		System.out.println("최고점수: " + max);
	}

	public static class Student {
		private String name;
		private String sex;
		private int score;
		
		public Student(String name, String sex, int score) {
			this.name = name;
			this.sex = sex;
			this.score = score;
		}

		public String getSex() { return sex; }
		public int getScore() { return score; }
	}
	
}
```

**function을 이용한 max 구하기**

```java
	
	public static int max2(ToIntFunction<Student> function) {
		int max = 0;
		int score = 0;
		
		for (Student student : list) {
			score = function.applyAsInt(student);
			if (max < score) {
				max = score;
			}
		}
		return max;
	}
	
	
	public static void main(String[] args) {
		
		
		int max2 = max2(s -> s.getScore());
		
```



<br/><br/>
<hr/>


